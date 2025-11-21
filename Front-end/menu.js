/**
 * Sistema de Menu e Layout
 * Sistema de Gestão Escolar - Instituto Recriar
 */

document.addEventListener('DOMContentLoaded', () => {
  // Verificar se é uma página que precisa do menu
  if (document.getElementById('menu-container')) {
    // Inicialmente define a opacidade como 0
    document.querySelector('main').style.opacity = '0';
    
    initializeLayout();
    setActiveMenuItem();
    setupMobileMenu();
    
    // Após um pequeno delay, faz o conteúdo aparecer suavemente
    setTimeout(() => {
      document.querySelector('main').style.opacity = '1';
    }, 50);
  }
});

/**
 * Inicializa o layout da página com menu e cabeçalho
 */
function initializeLayout() {
  const layoutHTML = `
    <!-- Barra superior -->
    <div class="top-bar">
      <div class="empresa-logo">Instituto Recriar</div>
      <div class="user-info">
        <a href="#" id="logout-btn">Sair</a>
      </div>
    </div>

    <!-- Menu lateral -->
    <aside class="menu-lateral" role="navigation" aria-label="Menu principal">
      <nav>
        <ul>
          <li><a href="menu.html" data-page="info-sistema">
            <span class="menu-icon">🏠</span>
            <span class="menu-text">Info do Sistema</span>
          </a></li>
          <li><a href="alunos.html" data-page="alunos">
            <span class="menu-icon">👨‍🎓</span>
            <span class="menu-text">Alunos</span>
          </a></li>
          <li><a href="professores.html" data-page="professores">
            <span class="menu-icon">👨‍🏫</span>
            <span class="menu-text">Professores</span>
          </a></li>
          <li><a href="turmas.html" data-page="turmas">
            <span class="menu-icon">📚</span>
            <span class="menu-text">Turmas</span>
          </a></li>
          <li><a href="responsaveis.html" data-page="responsaveis">
            <span class="menu-icon">👥</span>
            <span class="menu-text">Responsáveis</span>
          </a></li>
          <li><a href="admin.html" data-page="admin">
            <span class="menu-icon">⚙️</span>
            <span class="menu-text">Admin</span>
          </a></li>
        </ul>
      </nav>
    </aside>
  `;

  const container = document.getElementById('menu-container');
  if (container) {
    container.innerHTML = layoutHTML;

    // Aplicar restrições de visibilidade do menu baseado no tipo de usuário
    try {
      const userInfo = window.Auth ? Auth.getUserInfo() : null;
      const userType = (userInfo && (userInfo.userType || userInfo.type)) || sessionStorage.getItem('user_type') || 'PROFESSOR';

      // Mapeamento de permissões por página (padrão é ADMIN/PROFESSOR)
      const pageRoles = {
        'info-sistema': ['ADMIN', 'PROFESSOR'],
        alunos: ['ADMIN'],
        professores: ['ADMIN'],
        // Turmas agora visível apenas para ADMIN
        turmas: ['ADMIN'],
        responsaveis: ['ADMIN'],
        admin: ['ADMIN']
      };

      // Remover links que o usuário não tem permissão para ver
      const links = container.querySelectorAll('.menu-lateral a[data-page]');
      links.forEach(link => {
        const page = link.getAttribute('data-page');
        const allowed = pageRoles[page] || ['ADMIN', 'PROFESSOR'];
        if (!allowed.includes(userType)) {
          const li = link.closest('li');
          if (li) li.remove();
        }
      });
    } catch (err) {
      console.error('Erro ao aplicar restrições de menu:', err);
    }

    // Atualizar saudação do usuário
    updateUserGreeting();

    // Configurar eventos
    setupMenuEvents();
  }
}

/**
 * Atualiza a saudação do usuário no cabeçalho
 */
function updateUserGreeting() {
  const userGreeting = document.getElementById('username-display'); // ID ajustado
  if (userGreeting && window.Auth) {
    const userInfo = Auth.getUserInfo();
    
    if (userInfo && userInfo.username) {
      userGreeting.textContent = `Olá, ${userInfo.username}`;
    } else if (userInfo && userInfo.nome) { // Adicionado fallback para 'nome'
      userGreeting.textContent = `Olá, ${userInfo.nome}`;
    } else {
      userGreeting.textContent = 'Olá, Usuário';
    }
  }
}

/**
 * Define o item de menu ativo baseado na página atual
 */
function setActiveMenuItem() {
  const currentPage = getCurrentPageName();
  const menuLinks = document.querySelectorAll('.menu-lateral a[data-page]');
  
  menuLinks.forEach(link => {
    const linkPage = link.getAttribute('data-page');
    if (linkPage === currentPage) {
      link.classList.add('active');
      link.setAttribute('aria-current', 'page');
    } else {
      link.classList.remove('active');
      link.removeAttribute('aria-current');
    }
  });
}

/**
 * Obtém o nome da página atual
 */
function getCurrentPageName() {
  const path = window.location.pathname;
  const filename = path.split('/').pop();
  
  // Mapear arquivos para nomes de página
  const pageMap = {
    'menu.html': 'info-sistema',
    'alunos.html': 'alunos',
    'professores.html': 'professores',
    'turmas.html': 'turmas',
    'admin.html': 'admin',
    'novo-aluno.html': 'alunos',
    'alterar-aluno.html': 'alunos',
    'excluir-aluno.html': 'alunos',
    'novo-professor.html': 'professores',
    'alterar-professor.html': 'professores',
    'excluir-professor.html': 'professores',
    'nova-turma.html': 'turmas',
    'alterar-turma.html': 'turmas',
    'administrar-turma.html': 'turmas',
    'novo-usuario.html': 'admin',
    'alterar-usuario.html': 'admin',
    'excluir-usuario.html': 'admin',
    'responsaveis.html': 'responsaveis'
  };
  
  return pageMap[filename] || 'menu';
}

/**
 * Configura eventos do menu
 */
function setupMenuEvents() {
  // Evento de logout
const logoutBtn = document.getElementById("logout-btn");
if (logoutBtn) {
  logoutBtn.addEventListener("click", (e) => {
    e.preventDefault();
    Auth.logout();
  });
}
  
  // Eventos de navegação com feedback visual
  const menuLinks = document.querySelectorAll('.menu-lateral a');
  menuLinks.forEach(link => {
    link.addEventListener('click', function(e) {
      e.preventDefault();
      
      // Adicionar efeito visual de clique
      this.style.transform = 'scale(0.95)';
      
      // Armazenar o href para navegação
      const href = this.getAttribute('href');
      
      // Adicionar classe de transição ao conteúdo principal
      const mainElement = document.querySelector('main');
      if (mainElement) {
        mainElement.classList.add('page-transition-out');
      }
      
      // Aguardar a animação de fade-out e navegar
      setTimeout(() => {
        window.location.href = href;
      }, 300);
    });
  });
}

/**
 * Configura menu mobile
 */
function setupMobileMenu() {
  const mobileToggle = document.getElementById('mobile-menu-toggle');
  const menuLateral = document.querySelector('.menu-lateral');
  
  if (mobileToggle && menuLateral) {
    mobileToggle.addEventListener('click', function() {
      const isOpen = menuLateral.classList.contains('open');
      
      if (isOpen) {
        menuLateral.classList.remove('open');
        mobileToggle.setAttribute('aria-label', 'Abrir menu');
        mobileToggle.classList.remove('active');
      } else {
        menuLateral.classList.add('open');
        mobileToggle.setAttribute('aria-label', 'Fechar menu');
        mobileToggle.classList.add('active');
      }
    });
    
    // Fechar menu ao clicar em um link (mobile)
    const menuLinks = document.querySelectorAll('.menu-lateral a');
    menuLinks.forEach(link => {
      link.addEventListener('click', function() {
        if (window.innerWidth <= 768) {
          menuLateral.classList.remove('open');
          mobileToggle.classList.remove('active');
          mobileToggle.setAttribute('aria-label', 'Abrir menu');
        }
      });
    });
    
    // Fechar menu ao clicar fora (mobile)
    document.addEventListener('click', function(e) {
      if (window.innerWidth <= 768 && 
          !menuLateral.contains(e.target) && 
          !mobileToggle.contains(e.target) &&
          menuLateral.classList.contains('open')) {
        
        menuLateral.classList.remove('open');
        mobileToggle.classList.remove('active');
        mobileToggle.setAttribute('aria-label', 'Abrir menu');
      }
    });
  }
}

/**
 * Atualiza o título da página baseado no menu ativo
 */
function updatePageTitle() {
  const activeLink = document.querySelector('.menu-lateral a.active .menu-text');
  if (activeLink) {
    const pageTitle = activeLink.textContent;
    document.title = `${pageTitle} - Sistema de Gestão Escolar`;
  }
}

// Chamar atualização do título após carregar o menu
setTimeout(updatePageTitle, 100);

// Adicionar estilos específicos para o menu mobile
const mobileMenuStyles = `
<style>
.mobile-menu-toggle {
  display: none;
  flex-direction: column;
  background: none;
  border: none;
  cursor: pointer;
  padding: 0.5rem;
  margin-right: 1rem;
}

.mobile-menu-toggle span {
  width: 25px;
  height: 3px;
  background: white;
  margin: 3px 0;
  transition: 0.3s;
  border-radius: 2px;
}

.mobile-menu-toggle.active span:nth-child(1) {
  transform: rotate(-45deg) translate(-5px, 6px);
}

.mobile-menu-toggle.active span:nth-child(2) {
  opacity: 0;
}

.mobile-menu-toggle.active span:nth-child(3) {
  transform: rotate(45deg) translate(-5px, -6px);
}

.top-bar h1 {
  margin: 0;
  font-size: 1.25rem;
}

.top-nav {
  display: flex;
  align-items: center;
}

.user-actions {
  display: flex;
  align-items: center;
  gap: 1rem;
}

#user-greeting {
  color: #ecf0f1;
  font-size: 0.9rem;
}

.logout-btn {
  color: white;
  text-decoration: none;
  font-weight: bold;
  padding: 0.5rem 1rem;
  border-radius: 4px;
  transition: background-color 0.3s ease;
}

.logout-btn:hover {
  background-color: rgba(255,255,255,0.1);
}

.menu-icon {
  margin-right: 0.5rem;
  font-size: 1.1rem;
}

@media (max-width: 768px) {
  .mobile-menu-toggle {
    display: flex;
  }
  
  #user-greeting {
    display: none;
  }
  
  .top-bar h1 {
    font-size: 1rem;
  }
  
  .menu-lateral {
    z-index: 1001;
  }
}

@media (max-width: 480px) {
  .user-actions {
    gap: 0.5rem;
  }
  
  .logout-btn {
    padding: 0.25rem 0.5rem;
    font-size: 0.9rem;
  }
}
</style>
`;

// Adicionar estilos ao head
document.head.insertAdjacentHTML('beforeend', mobileMenuStyles);