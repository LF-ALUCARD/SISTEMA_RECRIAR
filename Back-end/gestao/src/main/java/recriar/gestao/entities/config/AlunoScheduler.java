package recriar.gestao.entities.config;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import recriar.gestao.entities.Aluno;
import recriar.gestao.repositories.AlunoRepository;

@Service
public class AlunoScheduler {

    @Autowired
    private AlunoRepository repositor;

    // Executa todo dia 1 de cada mês, à meia-noite
    @Scheduled(cron = "0 0 0 1 * *")
    public void atualizarIdadeAlunos() {
        List<Aluno> alunos = repositor.findAll();

        for (Aluno aluno : alunos) {
            if (aluno.getData_nascimento() != null) {
                int idade = calcularIdade(aluno.getData_nascimento());
                aluno.setIdade(idade);
            }
        }

        repositor.saveAll(alunos);

        System.out.println("✅ Idades dos alunos atualizadas com sucesso em " + LocalDate.now());
    }

    private int calcularIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}

