$(function () {
    $('#myForm').on('submit',function (e) {

              $.ajax({
                type: 'post',
                url: '/roleDices',
                data: $('#myForm').serialize(),
                success: function () {
              
                }
              });
          e.preventDefault();
        });
})