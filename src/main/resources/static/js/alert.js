document.addEventListener('DOMContentLoaded', function() {
    const success = document.getElementById('success');
    const warning = document.getElementById('warning');
    const error = document.getElementById('error');
    const note = document.getElementById('Note');
    const message = document.getElementById('message').value;

    if (success) {
        createToast(success.getAttribute('class'), message);
    }
    if (warning) {
        createToast(warning.getAttribute('class'), message);
    }
    if (error) {
        createToast(error.getAttribute('class'), message);
    }
});
function createToast(status, msg) {
    let toast = document.createElement('div');
    toast.className = `my-toast ${status}`;

    toast.innerHTML = `
        ${toasts[status].icon}
        <span class="msg">${msg}</span>
        <span class="countdown"></span>
    `;
    document.querySelector('#toasts').appendChild(toast);

    setTimeout(() => {
        toast.style.animation = 'hide_slide 1s ease forwards';
    }, 4000);
    setTimeout(() => {
        toast.remove();
    }, 6000);
}

const toasts = {
    success: {
        icon: '<i class="fas fa-check-circle top-element"></i>',
        msg: 'success!',
    },
    error: {
        icon: '<i class="fas fa-exclamation-triangle top-element"></i>',
        msg: 'error!',
    },
    warning: {
        icon: '<i class="fas fa-exclamation-circle top-element"></i>',
        msg: 'warning!',
    },
}
