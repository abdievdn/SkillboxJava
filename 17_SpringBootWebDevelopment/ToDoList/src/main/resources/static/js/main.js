$(function(){

    const appendTodo = function(data){
        let todoCode = data.number + '. <a href="#" class="todo-link" data-number="' + data.number + '">' + data.name + '</a>';
        $('#todo-list')
            .append('<div>' + todoCode + '</div>');
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

    //Show delete todo form
    $('#show-delete-todo-form').click(function(){
        $('#todo-delete-form').css('display', 'flex');
    });

    //Closing delete todo form
    $('#todo-delete-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Getting todo
    $(document).on('click', '.todo-link', function () {
        let link = $(this);
        let todoNumber = link.data('number');
        $.ajax({
            method: "GET",
            url: '/todolist/' + todoNumber,
            success: function(response)
            {
                let code = '<span> Note: ' + response.note + '</span>';
                link.parent().append(code);
            },
            error: function (response) {
                if (response.status == 404) {
                    alert('The case not found!');
                }
            }
        });
        return false;
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

});