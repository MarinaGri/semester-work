document.addEventListener('DOMContentLoaded', function() {
    const EMAIL_REG = /^([\w!#$%&'*+\/=?^_`\-{|}~]+|"[\w!#$%&'*+\/=?^_`\s{|}~.(),:;<>@\\\[\]]*")+[\w!#$%&'*+\/=?^_`{|}~.]*@\w+(\w-)*\.([A-Za-z]+)$/;

    let btn = document.getElementsByTagName('button')[0];
    btn.style.color = 'grey';
    btn.setAttribute('disabled', true);

    let counter = document.getElementsByClassName('count')[0];

    let email = document.getElementById('email');
    let pass = document.getElementById('password');

    let isValidPass = function (){
        if(pass.value === ''){
            return false;
        }
        let passHelp = document.getElementById('passwordHelp');
        if(pass.value.match(/[0-9]+/) === null){
            passHelp.innerText = 'Password must contains digit';
            return false;
        }
        if(pass.value.match(/[A-Z]+/) === null){
            passHelp.innerText = 'Password must contains capital letter';
            return false;
        }
        if(pass.value.match(/[a-z]+/) === null){
            passHelp.innerText = 'Password must contains lower case letter';
            return false;
        }
        passHelp.innerText = null;
        return true;
    }

    let isValidEmail = function (){
        if(email.value === ''){
            return false;
        }

        let emailHelp = document.getElementById('emailHelp');
        if(email.value.match(EMAIL_REG) === null) {
            emailHelp.innerText = 'Input string is not an email';
            return false;
        }
        emailHelp.innerText = null;
        return true;
    }

    let isValid = function (){
        counter.innerText = pass.value.length + '/20';
        if(isValidEmail() & isValidPass()){
            btn.style.color = 'white';
            btn.removeAttribute('disabled');
        } else {
            btn.style.color = 'grey';
            btn.setAttribute('disabled', true);
        }
        if (pass.value.length > 20 || pass.value.length < 5) {
            counter.style.color = 'red';
            if (pass.value.length > 20) {
                return false;
            }
        } else {
            counter.style.color = 'black';
            return true;
        }
    }

    let elements = ['firstName', 'lastName', 'email', 'password'];
    elements.forEach(elem => {
        let param = document.getElementById(elem);
        if (param !== null) {
            param.onkeypress = param.oninput = param.onchange =  param.onload =isValid;
        }
    })
});
