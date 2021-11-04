document.addEventListener('DOMContentLoaded', function() {

    btn = document.getElementById('deleteAcc');

    function showConfirm() {
        if (ctx) {

        if (confirm('By clicking "OK" you confirm your action')) {
            window.location.pathname = ctx + '/deleteAccount'
        } else {
            window.location.pathname = ctx + '/profile'
        }
    }
    }
    if(btn) {
        btn.addEventListener('click', showConfirm);
    }
});