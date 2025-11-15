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
        // Lê mensagem do backend
        let text = await response.text();
        let msg = "Falha no login.";

        try {
          const parsed = JSON.parse(text);
          msg = parsed.message || msg;
        } catch (_) {}

        throw new Error(msg);
      }

      const data = await response.json();

      sessionStorage.setItem('auth_token', data.token);
      sessionStorage.setItem('user_id', data.usuario.id);
      sessionStorage.setItem('user_name', data.usuario.nome);
      sessionStorage.setItem('user_type', data.usuario.tipo);
      sessionStorage.setItem('login_timestamp', new Date().toISOString());

      window.location.href = 'menu.html';
    } catch (error) {
      console.error('Erro no login:', error);
      alert(error.message);
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
    return true;
  }
};


// Utilitários de UI
const UI = {
  showMessage(message, type = 'info', containerId = 'feedback-message') {
    const container = document.getElementById(containerId);
    if (!container) return;

    container.textContent = message;
    container.className = `feedback-message ${type}`;
    container.style.display = 'block';

    if (type === 'success') {
      setTimeout(() => this.hideMessage(containerId), 5000);
    }
  },

  hideMessage(containerId = 'feedback-message') {
    const container = document.getElementById(containerId);
    if (container) container.style.display = 'none';
  },

  showButtonLoading(buttonId, loadingText = 'Carregando...') {
    const button = document.getElementById(buttonId);
    if (!button) return;
    button.disabled = true;
    button.dataset.originalText = button.textContent;
    button.innerHTML = `<span class="spinner"></span> ${loadingText}`;
  },

  hideButtonLoading(buttonId) {
    const button = document.getElementById(buttonId);
    if (!button) return;
    button.disabled = false;
    button.textContent = button.dataset.originalText || 'Enviar';
  },

  validateRequired(fieldId, errorId, message = 'Este campo é obrigatório.') {
    const field = document.getElementById(fieldId);
    const error = document.getElementById(errorId);
    if (!field || !error) return true;

    if (!field.value.trim()) {
      error.textContent = message;
      field.focus();
      return false;
    }
    error.textContent = '';
    return true;
  },

  validateEmail(fieldId, errorId) {
    const field = document.getElementById(fieldId);
    const error = document.getElementById(errorId);
    if (!field || !error) return true;

    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (field.value && !regex.test(field.value)) {
      error.textContent = 'Por favor, digite um e-mail válido.';
      field.focus();
      return false;
    }
    error.textContent = '';
    return true;
  },

  clearFormErrors(formId) {
    const form = document.getElementById(formId);
    if (!form) return;
    form.querySelectorAll('.error-message').forEach(e => e.textContent = '');
  },

  animateCounter(elementId, targetValue, duration = 1500) {
    const el = document.getElementById(elementId);
    if (!el) return;

    let val = 0;
    const inc = targetValue / (duration / 30);

    const timer = setInterval(() => {
      val += inc;
      if (val >= targetValue) {
        val = targetValue;
        clearInterval(timer);
      }
      el.textContent = Math.floor(val);
    }, 30);
  }
};

// Utilitários de formatação
const Format = {
  date(str) {
    if (!str) return '-';
    if (/^\d{4}-\d{2}-\d{2}$/.test(str)) {
      const [y, m, d] = str.split('-');
      return `${d}/${m}/${y}`;
    }
    return new Date(str).toLocaleDateString('pt-BR');
  },

  datetime(str) {
    if (!str) return '-';
    return new Date(str).toLocaleString('pt-BR');
  },

  phone(phone) {
    if (!phone) return '-';

    const n = phone.replace(/\D/g, '');
    if (n.length === 11) return n.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
    if (n.length === 10) return n.replace(/(\d{2})(\d{4})(\d{4})/, '($1) $2-$3');

    return phone;
  },

  capitalize(str) {
    if (!str) return '';
    return str.toLowerCase().replace(/\b\w/g, l => l.toUpperCase());
  }
};


// ======================================================================
//  CORREÇÃO GLOBAL: MÉTODOS DA API AGORA EXTRAEM SOMENTE O JSON.message
// ======================================================================

function extractErrorMessage(responseText, fallback) {
  try {
    const parsed = JSON.parse(responseText);
    return parsed.message || fallback;
  } catch {
    return fallback;
  }
}

// Utilitários de API
const API = {
  async get(endpoint) {
    try {
      const url = `${CONFIG.API_BASE_URL}${endpoint}`;
      const response = await fetch(url, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${sessionStorage.getItem('auth_token') || ''}`
        }
      });

      if (!response.ok) {
        const text = await response.text();
        throw new Error(extractErrorMessage(text, "Erro ao carregar dados."));
      }

      if (response.status === 204) return null;
      return response.headers.get('content-type')?.includes('application/json')
        ? response.json()
        : null;

    } catch (error) {
      console.error("API GET Error:", error);
      throw error;
    }
  },

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
        const text = await response.text();
        throw new Error(extractErrorMessage(text, "Erro ao enviar dados."));
      }

      if (response.status === 204) return null;
      return response.headers.get('content-type')?.includes('application/json')
        ? response.json()
        : null;

    } catch (error) {
      console.error("API POST Error:", error);
      throw error;
    }
  },

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
        const text = await response.text();
        throw new Error(extractErrorMessage(text, "Erro ao atualizar dados."));
      }

      if (response.status === 204) return null;
      return response.headers.get('content-type')?.includes('application/json')
        ? response.json()
        : null;

    } catch (error) {
      console.error("API PUT Error:", error);
      throw error;
    }
  },

  async delete(endpoint) {
    try {
      const response = await fetch(`${CONFIG.API_BASE_URL}${endpoint}`, {
        method: 'DELETE',
        headers: {
          'Authorization': `Bearer ${sessionStorage.getItem('auth_token') || ''}`
        }
      });

      if (!response.ok) {
        const text = await response.text();
        throw new Error(extractErrorMessage(text, "Erro ao excluir."));
      }

      return null;

    } catch (error) {
      console.error("API DELETE Error:", error);
      throw error;
    }
  }
};


// Utilitários de Tabela
const Table = {
  render(tableId, data, columns, actions = []) {
    const table = document.getElementById(tableId);
    if (!table) return;

    table.innerHTML = '';

    const thead = document.createElement('thead');
    const headerRow = document.createElement('tr');

    columns.forEach(col => {
      const th = document.createElement('th');
      th.textContent = col.header;
      headerRow.appendChild(th);
    });

    if (actions.length > 0) {
      const th = document.createElement('th');
      th.textContent = 'Ações';
      headerRow.appendChild(th);
    }

    thead.appendChild(headerRow);
    table.appendChild(thead);

    const tbody = document.createElement('tbody');

    data.forEach(item => {
      const row = document.createElement('tr');

      columns.forEach(col => {
        const td = document.createElement('td');
        let value = item[col.field];

        if (col.format === 'date') value = Format.date(value);
        else if (col.format === 'datetime') value = Format.datetime(value);
        else if (col.format === 'capitalize') value = Format.capitalize(value);

        td.textContent = value || '-';
        row.appendChild(td);
      });

      if (actions.length > 0) {
        const td = document.createElement('td');

        actions.forEach(action => {
          const btn = document.createElement('button');
          btn.textContent = action.label;
          btn.className = `btn btn-sm btn-${action.style || 'primary'}`;
          btn.addEventListener('click', () => action.handler(item));
          td.appendChild(btn);
        });

        row.appendChild(td);
      }

      tbody.appendChild(row);
    });

    table.appendChild(tbody);
  }
};


// Utilitários de Paginação
const Pagination = {
  render(paginationId, totalPages, currentPage, onPageChange) {
    const container = document.getElementById(paginationId);
    if (!container) return;

    container.innerHTML = '';

    const ul = document.createElement('ul');
    ul.className = 'pagination';

    const liPrev = document.createElement('li');
    liPrev.className = `page-item ${currentPage === 1 ? 'disabled' : ''}`;

    const aPrev = document.createElement('a');
    aPrev.className = 'page-link';
    aPrev.href = '#';
    aPrev.textContent = 'Anterior';

    aPrev.onclick = e => {
      e.preventDefault();
      if (currentPage > 1) onPageChange(currentPage - 1);
    };

    liPrev.appendChild(aPrev);
    ul.appendChild(liPrev);

    const maxPages = 5;
    let start = Math.max(1, currentPage - Math.floor(maxPages / 2));
    let end = Math.min(totalPages, start + maxPages - 1);

    if (end - start < maxPages - 1) {
      start = Math.max(1, end - maxPages + 1);
    }

    for (let i = start; i <= end; i++) {
      const li = document.createElement('li');
      li.className = `page-item ${i === currentPage ? 'active' : ''}`;

      const a = document.createElement('a');
      a.className = 'page-link';
      a.href = '#';
      a.textContent = i;

      a.onclick = e => {
        e.preventDefault();
        onPageChange(i);
      };

      li.appendChild(a);
      ul.appendChild(li);
    }

    const liNext = document.createElement('li');
    liNext.className = `page-item ${currentPage === totalPages ? 'disabled' : ''}`;

    const aNext = document.createElement('a');
    aNext.className = 'page-link';
    aNext.href = '#';
    aNext.textContent = 'Próximo';

    aNext.onclick = e => {
      e.preventDefault();
      if (currentPage < totalPages) onPageChange(currentPage + 1);
    };

    liNext.appendChild(aNext);
    ul.appendChild(liNext);

    container.appendChild(ul);
  }
};

// Exporta para uso global
window.CONFIG = CONFIG;
window.Auth = Auth;
window.UI = UI;
window.Format = Format;
window.API = API;
window.Table = Table;
window.Pagination = Pagination;