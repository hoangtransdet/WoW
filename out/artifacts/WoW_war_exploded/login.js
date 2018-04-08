/**
 * Created by Nathan on 3/16/2016.
 */
(function (){
    var $window = $(window),
        $loginForm = $('#loginForm'),
        $backgroundImage = $('#backgroundImage');

    var repositionWindow = function (){
        $('#backgroundImage').offset({
            top: ($window.height() - $backgroundImage.height()) / 2,
            left: ($window.width() - $backgroundImage.width()) / 2
        });

        $('#loginForm').offset({
            top: $('#backgroundImage').offset().top + 500,
            left: $('#backgroundImage').offset().left + 425
        });
    }

    repositionWindow();

    $(window).on('resize', function(){
        repositionWindow();
    });

}());
