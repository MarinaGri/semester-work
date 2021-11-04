document.addEventListener('DOMContentLoaded', function() {
    let edits = document.getElementsByClassName('edit');

    let editText = function (i){
        return document.getElementById(i).getElementsByClassName('com')[0].innerText
    }

    let editHtml = function (text, id) {
        return`<div class="mb-3"><textarea name="comment" class="form-control" rows="2">${text}</textarea></div><button type="submit" name="save" value="${id}" class="button">Save</button>`
    }

    let editFun = function (id){
        let innerText = editText(id)
        document.getElementById(id).innerHTML = editHtml(innerText, id);
    }

    Array.from(edits).forEach(element => {
        element.addEventListener('click', () => {
            editFun(element.id)
        })
    });
})
