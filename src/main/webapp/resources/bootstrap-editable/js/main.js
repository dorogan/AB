$(document).ready(function() {
    //toggle `popup` / `inline` mode
    $.fn.editable.defaults.mode = 'inline';

    var n;
    $('.name').click(
        function(){
            n = $('.name').editable('getValue')
        }
    );
/*
    $('.done-action').click(
        function (){
            //var id = $('.done-action').data('id');
            var id = $('.done-action').attr('data-id');
            $.ajax({
                url:'/done/'.concat(id),
                success: function(){
                    $('#actions').load();
                }
            });
        }
    );

*/
    $("#myonoffswitch").click(function () {
        if(document.getElementById("myonoffswitch").checked == true){
            $("#act-time").show();
        }
        else{
            $("#act-time").hide();
            document.getElementById("act-time").value = null;
        }
    });



    //make name editable
    $('.name').editable({
        type: 'text',
        title: 'Action',
        placement: 'right',
        pk: 1,
        name: 'name',
        value: n,
        success: function(){
            $('#actions').load();
            n = '';
        }
    });

    //make new-action editable
    $('#new-action').editable({
        inputclass: 'mytext',
        type: 'text',
        cols: 20,
        pk: 1,
        url:'add',
        title: 'Add action',
        placement: 'right',
        name: 'new-action',
        success: function(){
            //$('html').reload(true)
            window.location = "/index";
        }
    });

    $("#submit-update").click(
        function(){
            if(document.getElementById("act-time").value.split(":").length - 1 < 2){
                var time = document.getElementById("act-time").value.concat(":00");
                document.getElementById("act-time").value = time;
            }
            if(document.getElementById("myonoffswitch").checked == false){
                document.getElementById("act-time").value = "00:00";
            }
            var id = document.getElementById("id").value;
            var msg = $("#act-form-details").serialize();
            $.ajax({
                url: '/update/'.concat(id),
                type: 'POST',
                data: msg,
                success: function(data){
                    $("#details-action").hide();
                    window.location = "/index";
                }
            });
        }
    );

/*
    $('#act-date').editable({
        type:  'date',
        pk:    1,
        name:  'act-    date',
        url:   '/',
        title: 'Select Date of birth'
    });*/

    //make status editable
    $('#status').editable({
        type: 'select',
        title: 'Select status',
        placement: 'right',
        value: 2,
        source: [
            {value: 1, text: 'status 1'},
            {value: 2, text: 'status 2'},
            {value: 3, text: 'status 3'}
        ]
        /*
        //uncomment these lines to send data on server
        ,pk: 1
        ,url: '/post'
        */
    });
});