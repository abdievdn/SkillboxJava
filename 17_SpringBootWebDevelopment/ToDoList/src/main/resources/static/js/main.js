$(function(){

    const appendTodo = function(data){
        let todoCode = '<h3 data-id="' + data.number + '">' + data.number + '. ' + data.name + '</h3>' + data.note + '<p><button' +
            ' id="delete-todo">Delete</button></p><hr>';
        $('#todo-list')
            .append('<div style="case">' + todoCode + '</div>');
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
        return false;
    });

    //Deleting todo
    $('#delete-todo').click(function()
    {
        let link = $(this);
        let todoNumber = link.data('number');
        $.ajax({
            method: "DELETE",
            url: '/todolist/' + todoNumber,
            success: function()
            {
                location.reload();
            },
            error: function (response) {
                if (response.status == 404) {
                    alert('The case not found!');
                }
            }
        });
        return false;
    });

});