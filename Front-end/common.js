/**
 * Arquivo com funcionalidades comuns compartilhadas entre páginas
 * Sistema de Gestão Escolar - Instituto Recriar
 */

// Configurações globais
const CONFIG = {
  SESSION_TIMEOUT: 30 * 60 * 1000, // 30 minutos em millisegundos
  API_BASE_URL: 'http://localhost:8080/api', // Base URL para APIs (será configurada pelo backend)
  ITEMS_PER_PAGE: 10
};

// Utilitários de autenticação
const Auth = {
  /**
   * Verifica se o usuário está logado
   */
  isLoggedIn() {
    const token = sessionStorage.getItem('auth_token');
    const timestamp = sessionStorage.getItem('login_timestamp');

    if (!token || !timestamp) {
      return false;
    }

    // Verificar se a sessão não expirou
    const loginTime = new Date(timestamp);
    const now = new Date();
    const timeDiff = now.getTime() - loginTime.getTime();

    if (timeDiff > CONFIG.SESSION_TIMEOUT) {
      this.logout();
      return false;
    }

    return true;
  },

  /**
   * Obtém informações do usuário logado
   */
  getUserInfo() {
    return {
      token: sessionStorage.getItem('auth_token'),
      userType: sessionStorage.getItem('user_type'),
      loginTime: sessionStorage.getItem('login_timestamp')
    };
  },

  /**
   * Realiza logout do usuário
   */
  logout() {
    sessionStorage.removeItem('auth_token');
    sessionStorage.removeItem('user_type');
    sessionStorage.removeItem('login_timestamp');
    window.location.href = 'index.html';
  },

  /**
   * Verifica autenticação e redireciona se necessário
   */
  requireAuth() {
    if (!this.isLoggedIn()) {
      alert('Sessão expirada. Por favor, faça login novamente.');
      window.location.href = 'index.html';
      return false;
    }
    return true;
  }
};

// Utilitários de UI
const UI = {
  /**
   * Mostra mensagem de feedback
   */
  showMessage(message, type = 'info', containerId = 'feedback-message') {
    const container = document.getElementById(containerId);
    if (!container) return;

    container.textContent = message;
    container.className = `feedback-message ${type}`;
    container.style.display = 'block';

    // Auto-hide após 5 segundos para mensagens de sucesso
    if (type === 'success') {
      setTimeout(() => {
        this.hideMessage(containerId);
      }, 5000);
    }
  },

  /**
   * Esconde mensagem de feedback
   */
  hideMessage(containerId = 'feedback-message') {
    const container = document.getElementById(containerId);
    if (container) {
      container.style.display = 'none';
    }
  },

  /**
   * Mostra estado de loading em um botão
   */
  showButtonLoading(buttonId, loadingText = 'Carregando...') {
    const button = document.getElementById(buttonId);
    if (!button) return;

    button.disabled = true;
    button.dataset.originalText = button.textContent;
    button.innerHTML = `<span class="spinner"></span> ${loadingText}`;
  },

  /**
   * Remove estado de loading de um botão
   */
  hideButtonLoading(buttonId) {
    const button = document.getElementById(buttonId);
    if (!button) return;

    button.disabled = false;
    button.textContent = button.dataset.originalText || 'Enviar';
  },

  /**
   * Valida campo obrigatório
   */
  validateRequired(fieldId, errorId, message = 'Este campo é obrigatório.') {
    const field = document.getElementById(fieldId);
    const errorElement = document.getElementById(errorId);

    if (!field || !errorElement) return true;

    if (!field.value.trim()) {
      errorElement.textContent = message;
      field.focus();
      return false;
    }

    errorElement.textContent = '';
    return true;
  },

  /**
   * Valida email
   */
  validateEmail(fieldId, errorId) {
    const field = document.getElementById(fieldId);
    const errorElement = document.getElementById(errorId);

    if (!field || !errorElement) return true;

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (field.value && !emailRegex.test(field.value)) {
      errorElement.textContent = 'Por favor, digite um e-mail válido.';
      field.focus();
      return false;
    }

    errorElement.textContent = '';
    return true;
  },

  /**
   * Limpa todas as mensagens de erro de um formulário
   */
  clearFormErrors(formId) {
    const form = document.getElementById(formId);
    if (!form) return;

    const errorElements = form.querySelectorAll('.error-message');
    errorElements.forEach(element => {
      element.textContent = '';
    });
  },

  /**
   * Anima contador numérico
   */
  animateCounter(elementId, targetValue, duration = 1500) {
    const element = document.getElementById(elementId);
    if (!element) return;

    let currentValue = 0;
    const increment = targetValue / (duration / 30);

    const timer = setInterval(() => {
      currentValue += increment;
      if (currentValue >= targetValue) {
        currentValue = targetValue;
        clearInterval(timer);
      }
      element.textContent = Math.floor(currentValue);
    }, 30);
  }
};

// Utilitários de formatação
const Format = {
  /**
   * Formata data para exibição
   */
  date(dateString) {
    if (!dateString) return '-';
    const date = new Date(dateString);
    return date.toLocaleDateString('pt-BR');
  },

  /**
   * Formata data e hora para exibição
   */
  datetime(dateString) {
    if (!dateString) return '-';
    const date = new Date(dateString);
    return date.toLocaleString('pt-BR');
  },

  /**
   * Formata telefone
   */
  phone(phone) {
    if (!phone) return '-';
    // Remove caracteres não numéricos
    const cleaned = phone.replace(/\D/g, '');

    // Formata conforme o tamanho
    if (cleaned.length === 11) {
      return cleaned.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
    } else if (cleaned.length === 10) {
      return cleaned.replace(/(\d{2})(\d{4})(\d{4})/, '($1) $2-$3');
    }

    return phone;
  },

  /**
   * Capitaliza primeira letra de cada palavra
   */
  capitalize(str) {
    if (!str) return '';
    return str.toLowerCase().replace(/\b\w/g, l => l.toUpperCase());
  }
};

// Utilitários de API (preparação para backend)
const API = {
  /**
   * Realiza requisição GET
   */
  async get(endpoint) {
    try {
      const url = `${CONFIG.API_BASE_URL}${endpoint}`;
      console.debug(`API GET -> ${url}`);
      const response = await fetch(url, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${sessionStorage.getItem('auth_token') || ''}`
        }
      });
      if (!response.ok) {
        // Tentar ler corpo da resposta para dar mais contexto
        let bodyText = '';
        try {
          bodyText = await response.text();
        } catch (e) {
          bodyText = '';
        }
        const errMsg = `HTTP error! status: ${response.status}${bodyText ? ' - ' + bodyText : ''}`;
        console.error(`API GET Error response -> ${url}:`, response.status, bodyText);
        throw new Error(errMsg);
      }

      // Some endpoints may return 204 No Content or non-JSON responses
      if (response.status === 204) return null;
      const ct = response.headers.get('content-type') || '';
      if (ct.includes('application/json')) return await response.json();
      return null;
    } catch (error) {
      console.error('API GET Error:', error);
      throw error;
    }
  },

  /**
   * Realiza requisição POST
   */
  async post(endpoint, data) {
    try {
      const response = await fetch(`${CONFIG.API_BASE_URL}${endpoint}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${sessionStorage.getItem('auth_token') || ''}`
        },
        body: JSON.stringify(data)
      });

      if (!response.ok) {
        // Tentar ler o corpo da resposta para fornecer contexto do erro
        let bodyText = '';
        try {
          bodyText = await response.text();
        } catch (e) {
          bodyText = '';
        }
        throw new Error(`HTTP error! status: ${response.status}${bodyText ? ' - ' + bodyText : ''}`);
      }

      if (response.status === 204) return null;
      const ct = response.headers.get('content-type') || '';
      if (ct.includes('application/json')) return await response.json();
      return null;
    } catch (error) {
      console.error('API POST Error:', error);
      throw error;
    }
  },

  /**
   * Realiza requisição PUT
   */
  async put(endpoint, data) {
    try {
      const response = await fetch(`${CONFIG.API_BASE_URL}${endpoint}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${sessionStorage.getItem('auth_token') || ''}`
        },
        body: JSON.stringify(data)
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      if (response.status === 204) return null;
      const ct = response.headers.get('content-type') || '';
      if (ct.includes('application/json')) return await response.json();
      return null;
    } catch (error) {
      console.error('API PUT Error:', error);
      throw error;
    }
  },

  /**
   * Realiza requisição DELETE
   */
  async delete(endpoint) {
    try {
      const response = await fetch(`${CONFIG.API_BASE_URL}${endpoint}`, {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${sessionStorage.getItem('auth_token') || ''}`
        }
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      if (response.status === 204) return null;
      const ct = response.headers.get('content-type') || '';
      if (ct.includes('application/json')) return await response.json();
      return null;
    } catch (error) {
      console.error('API DELETE Error:', error);
      throw error;
    }
  }
};

// Utilitários de tabela
const Table = {
  /**
   * Cria linha de tabela com dados
   */
  createRow(data, columns, actions = []) {
    const row = document.createElement('tr');

    // Adicionar colunas de dados
    columns.forEach(column => {
      const cell = document.createElement('td');

      if (typeof column === 'string') {
        cell.textContent = data[column] || '-';
      } else if (typeof column === 'object') {
        if (column.format) {
          cell.textContent = column.format(data[column.field]);
        } else {
          cell.textContent = data[column.field] || '-';
        }
      }

      row.appendChild(cell);
    });

    // Adicionar coluna de ações se houver
    if (actions.length > 0) {
      const actionsCell = document.createElement('td');
      actionsCell.className = 'actions';

      actions.forEach(action => {
        const button = document.createElement('button');
        button.textContent = action.label;
        button.className = action.class || '';
        button.onclick = () => action.handler(data);
        actionsCell.appendChild(button);
      });

      row.appendChild(actionsCell);
    }

    return row;
  },

  /**
   * Limpa corpo da tabela
   */
  clearBody(tableId) {
    const table = document.getElementById(tableId);
    if (!table) return;

    const tbody = table.querySelector('tbody');
    if (tbody) {
      tbody.innerHTML = '';
    }
  },

  /**
   * Adiciona linha à tabela
   */
  addRow(tableId, row) {
    const table = document.getElementById(tableId);
    if (!table) return;

    let tbody = table.querySelector('tbody');
    if (!tbody) {
      tbody = document.createElement('tbody');
      table.appendChild(tbody);
    }

    tbody.appendChild(row);
  }
};

// Inicialização comum para todas as páginas
document.addEventListener('DOMContentLoaded', async function() {
  // Verificar se não é a página de login
  if (!window.location.pathname.includes('index.html') &&
      !window.location.pathname.endsWith('/')) {

    // Verificar autenticação para páginas protegidas
    if (!Auth.isLoggedIn()) {
      Auth.logout();
      return;
    }

    // Atualizar informações do usuário se existir elemento
    const usernameDisplay = document.getElementById('username-display');
    const loginTimeDisplay = document.getElementById('login-time');

    if (usernameDisplay || loginTimeDisplay) {
      const userInfo = Auth.getUserInfo();

      if (usernameDisplay) {
        try {
          // Preferir endpoint com id: /user/info/{id} quando tivermos o id em sessionStorage
          const storedId = sessionStorage.getItem('user_id');
          let accountInfo = null;
          if (storedId) {
            accountInfo = await API.get(`/user/info/${encodeURIComponent(storedId)}`);
          } else {
            // fallback para endpoint antigo caso não haja id
            accountInfo = await API.get('/user/account-info');
          }

          // Prefer 'nome' then 'name' then 'email'. Mostrar apenas o primeiro nome na saudação.
          const raw = accountInfo && (accountInfo.nome || accountInfo.name || accountInfo.email) ? (accountInfo.nome || accountInfo.name || accountInfo.email) : 'Usuário';
          const first = String(raw).split(' ')[0];
          usernameDisplay.textContent = `Olá, ${first}`;
        } catch (error) {
          console.error('Erro ao buscar informações da conta:', error);
          usernameDisplay.textContent = 'Olá, Usuário';
        }
      }

      if (loginTimeDisplay && userInfo.loginTime) {
        const loginDate = new Date(userInfo.loginTime);
        loginTimeDisplay.textContent = Format.datetime(loginDate);
      }
    }
  }

  // Configurar logout automático em links de sair
  const logoutLinks = document.querySelectorAll('a[href="index.html"]');
  logoutLinks.forEach(link => {
    link.addEventListener('click', function(e) {
      e.preventDefault();
      Auth.logout();
    });
  });
});

// Exportar para uso global
window.CONFIG = CONFIG;
window.Auth = Auth;
window.UI = UI;
window.Format = Format;
window.API = API;
window.Table = Table;
