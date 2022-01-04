$(function(){

    const appendTodo = function(data){
        let todoCode = '<h3>' + data.number + '. ' + data.name + '</h3>' + data.note +
            '<p><button class="delete-todo" data-number="' + data.number + '">Delete</button>&nbsp;' +
            '<button class="get-todo" data-number="' + data.number + '">Get</button></p><hr>';
        $('#todo-list')
            .append('<div class="case">' + todoCode + '</div>');
    };

    //Output todolist on load page
   $.get('/todolist/', function(response)
   {
       for(let i in response) {
           appendTodo(response[i]);
       }
   });

    //Show adding todo form
    $('#show-add-todo-form').click(function(){
        $('#todo-form').css('display', 'flex');
    });

    //Closing adding todo form
    $('#todo-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Adding todo
    $('#save-todo').click(function()
    {
        let data = $('#todo-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/todolist/',
            data: data,
            success: function(response)
            {
                $('#todo-form').css('display', 'none');
                let todo = {};
                todo.number = response;
                let dataArray = $('#todo-form form').serializeArray();
                for(let i in dataArray) {
                    todo[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTodo(todo);
            }
        });
        $('#todo-form').reset();
        return false;
    });

    //Getting todo
    $(document).on('click', '.get-todo', function(){
        let getButton = $(this);
        let todoNumber = getButton.data('number');
        $.ajax({
            method: "GET",
            url: '/todolist/' + todoNumber,
            success: function(response)
            {
                $('#get-todo-form').css('display', 'flex');
                $('#edit-number').val(response.number);
                $('#edit-name').val(response.name);
                $('#edit-note').val(response.note);
            },
            error: function(response)
            {
                if(response.status == 404) {
                    alert('Case not found!');
                }
            }
        });
        return false;
    });

    //Closing get todo form
    $('#get-todo-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    $('#edit-todo').click(function()
    {
        let editButton = $(this);
        let todoNumber = editButton.name('number');
        $.ajax({
            method: "PUT",
            url: '/todolist/' + todoNumber,
            success: function()
            {
                $('#get-todo-form').css('display', 'none');
                let todo = {};
                let dataArray = $('#get-todo-form form').serializeArray();
                for(let i in dataArray) {
                    todo[dataArray[i]['name']] = dataArray[i]['value'];
                }
            }
        });
        return false;
    });

    //Deleting todo
    $(document).on('click', '.delete-todo', function()
    {
        let deleteButton = $(this);
        let todoNumber = deleteButton.data('number');
        $.ajax({
            method: "DELETE",
            url: '/todolist/' + todoNumber,
            success: function()
            {
                location.reload();
            },
        });
        return false;
    });

    //Delete all
    $('#delete-all').click(function()
    {
        $.ajax({
            method: "DELETE",
            url: '/todolist/delete_all',
            success: function()
            {
                location.reload();
            },
        });
        return false;
    });
});