/**
 * Sistema de Menu e Layout
 * Sistema de Gestão Escolar - Instituto Recriar
 */

document.addEventListener('DOMContentLoaded', () => {
  // Verificar se é uma página que precisa do menu
  if (document.getElementById('menu-container')) {
    initializeLayout();
    setActiveMenuItem();
    setupMobileMenu();
  }
});

/**
 * Inicializa o layout da página com menu e cabeçalho
 */
function initializeLayout() {
  const layoutHTML = `
    <!-- Barra superior -->
    <header class="top-bar" role="banner">
      <div class="empresa-logo">
        <h1>Instituto Recriar</h1>
      </div>
      <nav class="top-nav">
        <button id="mobile-menu-toggle" class="mobile-menu-toggle" aria-label="Abrir menu">
          <span></span>
          <span></span>
          <span></span>
        </button>
        <div class="user-actions">
          <span id="user-greeting">Olá, Usuário</span>
          <a href="index.html" class="logout-btn" title="Sair do sistema">Sair</a>
        </div>
      </nav>
    </header>

    <!-- Menu lateral -->
    <aside class="menu-lateral" role="navigation" aria-label="Menu principal">
      <nav>
        <ul>
          <li><a href="menu.html" data-page="menu">
            <span class="menu-icon">🏠</span>
            <span class="menu-text">Início</span>
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
          <li><a href="aulas.html" data-page="aulas">
            <span class="menu-icon">📅</span>
            <span class="menu-text">Aulas</span>
          </a></li>
          <li><a href="relatorios.html" data-page="relatorios">
            <span class="menu-icon">📊</span>
            <span class="menu-text">Relatórios</span>
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
  const userGreeting = document.getElementById('user-greeting');
  if (userGreeting && window.Auth) {
    const userInfo = Auth.getUserInfo();
    if (userInfo.username) {
      userGreeting.textContent = `Olá, ${userInfo.username}`;
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
    'menu.html': 'menu',
    'alunos.html': 'alunos',
    'professores.html': 'professores',
    'turmas.html': 'turmas',
    'aulas.html': 'aulas',
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
    'relatorios.html': 'relatorios',
    'novo-usuario.html': 'admin',
    'alterar-usuario.html': 'admin',
    'excluir-usuario.html': 'admin'
  };
  
  return pageMap[filename] || 'menu';
}

/**
 * Configura eventos do menu
 */
function setupMenuEvents() {
  // Evento de logout
  const logoutBtn = document.querySelector('.logout-btn');
  if (logoutBtn) {
    logoutBtn.addEventListener('click', function(e) {
      e.preventDefault();
      
      if (confirm('Tem certeza que deseja sair do sistema?')) {
        if (window.Auth) {
          Auth.logout();
        } else {
          window.location.href = 'index.html';
        }
      }
    });
  }
  
  // Eventos de navegação com feedback visual
  const menuLinks = document.querySelectorAll('.menu-lateral a');
  menuLinks.forEach(link => {
    link.addEventListener('click', function(e) {
      // Adicionar efeito visual de clique
      this.style.transform = 'scale(0.95)';
      setTimeout(() => {
        this.style.transform = '';
      }, 150);
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
