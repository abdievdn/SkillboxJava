$(function(){

    const appendTodo = function(data){
        let todoCode = '<h3>' + data.number + '. ' + data.name + '</h3>' + data.note +
            '<p><button class="delete-todo" data-number="' + data.number + '">Delete</button>&nbsp;' +
            '<button id="show-edit-todo-form" data-number="' + data.number + '">Edit</button></p><hr>';
        $('#todo-list')
            .append('<div class="case">' + todoCode + '</div>');
    };

    //Output todolist on load page
   $.get('/todolist/', function(response)
   {
       for(i in response) {
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
                for(i in dataArray) {
                    todo[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTodo(todo);
            }
        });
        $('#todo-form').reset();
        return false;
    });

    // Show edit todo form
    $('#show-edit-todo-form').click(function(){
        $('#todo-form').css('display', 'flex');
    });

    //Edit todo
    $('#edit-todo').click(function()
    {
        let data = $('#todo-form form').serialize();
        let editButton = $(this);
        let todoNumber = editButton.data('number');
        $.ajax({
            method: "PUT",
            url: '/todolist/' + todoNumber,
            data: data,
            success: function(response)
            {
                $('#todo-form').css('display', 'none');
                let todo = {};
                todo.number = response;
                let dataArray = $('#todo-form form').serializeArray();
                for(i in dataArray) {
                    todo[dataArray[i]['name']] = dataArray[i]['value'];
                }
                appendTodo(todo);
                location.reload();
            }
        });
        $('#todo-form').reset();
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

});