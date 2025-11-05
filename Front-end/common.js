/**
 * Arquivo com funcionalidades comuns compartilhadas entre páginas
 * Sistema de Gestão Escolar - Instituto Recriar
 */

// Configurações globais
const CONFIG = {
  SESSION_TIMEOUT: 30 * 60 * 1000, // 30 minutos em millisegundos
  API_BASE_URL: 'http://137.131.197.181:8080/api', // Base URL para APIs (será configurada pelo backend)
  ITEMS_PER_PAGE: 10
};

// Utilitários de autenticação
const Auth = {
  /**
   * Realiza o login do usuário e salva dados na sessão
   */
  async login(email, senha) {
    try {
      const response = await fetch(`${CONFIG.API_BASE_URL}/auth/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, senha })
      });

      if (!response.ok) {
        throw new Error('Falha no login. Verifique suas credenciais.');
      }

      const data = await response.json();

      // Salvar dados no sessionStorage
      sessionStorage.setItem('auth_token', data.token);
      sessionStorage.setItem('user_id', data.usuario.id);
      sessionStorage.setItem('user_name', data.usuario.nome); // O nome já está sendo salvo
      sessionStorage.setItem('user_type', data.usuario.tipo);
      sessionStorage.setItem('login_timestamp', new Date().toISOString());

      // Redirecionar para o menu principal
      window.location.href = 'menu.html';
    } catch (error) {
      console.error('Erro no login:', error);
      alert('Erro ao efetuar login. Tente novamente.');
    }
  },

  /**
   * Verifica se o usuário está logado
   */
  isLoggedIn() {
    const token = sessionStorage.getItem('auth_token');
    const timestamp = sessionStorage.getItem('login_timestamp');
    if (!token || !timestamp) return false;

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
      userId: sessionStorage.getItem('user_id'),
      userName: sessionStorage.getItem('user_name'),
      userType: sessionStorage.getItem('user_type'),
      loginTime: sessionStorage.getItem('login_timestamp')
    };
  },

  /**
   * Realiza logout do usuário
   */
  logout() {
    sessionStorage.clear();
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

    // REMOVENDO: A lógica de atualização    // A saudação será atualizada pelo menu.js, que é o responsável pelo layout.
    // A lógica de autenticação deve apenas garantir que o usuário está logado. }

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
    // Se vier só yyyy-MM-dd, tratar como local
    if (/^\d{4}-\d{2}-\d{2}$/.test(dateString)) {
      const [ano, mes, dia] = dateString.split('-');
      return `${dia}/${mes}/${ano}`;
    }
    // Se vier com hora, tratar como UTC
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
          'Authorization': `Bearer ${sessionStorage.getItem('auth_token') || ''}`
        }
      });

      if (!response.ok) {
        let bodyText = '';
        try {
          bodyText = await response.text();
        } catch (e) {
          bodyText = '';
        }
        throw new Error(`HTTP error! status: ${response.status}${bodyText ? ' - ' + bodyText : ''}`);
      }

      // DELETE geralmente retorna 204 No Content
      return null;
    } catch (error) {
      console.error('API DELETE Error:', error);
      throw error;
    }
  }
};

// Utilitários de Tabela
const Table = {
  /**
   * Renderiza uma tabela a partir de um array de objetos
   */
  render(tableId, data, columns, actions = []) {
    const table = document.getElementById(tableId);
    if (!table) return;

    // Limpa o conteúdo anterior
    table.innerHTML = '';

    // Cria o cabeçalho
    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');
    columns.forEach(col => {
      const th = document.createElement('th');
      th.textContent = col.header;
      headerRow.appendChild(th);
    });
    if (actions.length > 0) {
      const thActions = document.createElement('th');
      thActions.textContent = 'Ações';
      headerRow.appendChild(thActions);
    }
    thead.appendChild(headerRow);
    table.appendChild(thead);

    // Cria o corpo da tabela
    const tbody = document.createElement('tbody');
    data.forEach(item => {
      const row = document.createElement('tr');
      
      columns.forEach(col => {
        const td = document.createElement('td');
        let value = item[col.field];
        
        // Aplica formatação se houver
        if (col.format) {
          if (col.format === 'date') {
            value = Format.date(value);
          } else if (col.format === 'datetime') {
            value = Format.datetime(value);
          } else if (col.format === 'capitalize') {
            value = Format.capitalize(value);
          }
          // Adicione mais formatos conforme necessário
        }
        
        td.textContent = value || '-';
        row.appendChild(td);
      });

      // Adiciona coluna de ações
      if (actions.length > 0) {
        const tdActions = document.createElement('td');
        actions.forEach(action => {
          const button = document.createElement('button');
          button.textContent = action.label;
          button.className = `btn btn-sm btn-${action.style || 'primary'}`;
          button.addEventListener('click', () => action.handler(item));
          tdActions.appendChild(button);
        });
        row.appendChild(tdActions);
      }

      tbody.appendChild(row);
    });
    table.appendChild(tbody);
  }
};

// Utilitários de Paginação
const Pagination = {
  /**
   * Renderiza os controles de paginação
   */
  render(paginationId, totalPages, currentPage, onPageChange) {
    const container = document.getElementById(paginationId);
    if (!container) return;

    container.innerHTML = '';
    
    const ul = document.createElement('ul');
    ul.className = 'pagination';

    // Botão Anterior
    const liPrev = document.createElement('li');
    liPrev.className = `page-item ${currentPage === 1 ? 'disabled' : ''}`;
    const aPrev = document.createElement('a');
    aPrev.className = 'page-link';
    aPrev.href = '#';
    aPrev.textContent = 'Anterior';
    aPrev.addEventListener('click', (e) => {
      e.preventDefault();
      if (currentPage > 1) {
        onPageChange(currentPage - 1);
      }
    });
    liPrev.appendChild(aPrev);
    ul.appendChild(liPrev);

    // Links de Páginas
    const maxPagesToShow = 5;
    let startPage = Math.max(1, currentPage - Math.floor(maxPagesToShow / 2));
    let endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);

    if (endPage - startPage + 1 < maxPagesToShow) {
      startPage = Math.max(1, endPage - maxPagesToShow + 1);
    }

    for (let i = startPage; i <= endPage; i++) {
      const li = document.createElement('li');
      li.className = `page-item ${i === currentPage ? 'active' : ''}`;
      const a = document.createElement('a');
      a.className = 'page-link';
      a.href = '#';
      a.textContent = i;
      a.addEventListener('click', (e) => {
        e.preventDefault();
        onPageChange(i);
      });
      li.appendChild(a);
      ul.appendChild(li);
    }

    // Botão Próximo
    const liNext = document.createElement('li');
    liNext.className = `page-item ${currentPage === totalPages ? 'disabled' : ''}`;
    const aNext = document.createElement('a');
    aNext.className = 'page-link';
    aNext.href = '#';
    aNext.textContent = 'Próximo';
    aNext.addEventListener('click', (e) => {
      e.preventDefault();
      if (currentPage < totalPages) {
        onPageChange(currentPage + 1);
      }
    });
    liNext.appendChild(aNext);
    ul.appendChild(liNext);

    container.appendChild(ul);
  }
};

// Exporta os módulos para uso global
window.CONFIG = CONFIG;
window.Auth = Auth;
window.UI = UI;
window.Format = Format;
window.API = API;
window.Table = Table;
window.Pagination = Pagination;