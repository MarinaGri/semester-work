document.addEventListener('DOMContentLoaded', function() {
    let edits = document.getElementsByClassName('plus');

    let editHtml = function (id) {
        return`<div class="mb-3"><textarea name="comment" class="form-control" rows="2"></textarea></div><button type="submit" name="saveComment" value="${id}" class="button">Save</button>`
    }

    let editFun = function (id){
        document.getElementById(id).innerHTML = editHtml(id);
    }

    Array.from(edits).forEach(element => {
        element.addEventListener('click', () => {
            editFun(element.id)
        })
    });
})
