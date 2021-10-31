document.addEventListener('DOMContentLoaded', function() {

    function showConfirm() {
        if (ctx) {

        if (confirm('By clicking "OK" you confirm your action')) {
            window.location.pathname = ctx + '/deleteAccount'
        } else {
            window.location.pathname = ctx + '/profile'
        }
    }
    }
    document.getElementById('deleteAcc').addEventListener('click', showConfirm);
});