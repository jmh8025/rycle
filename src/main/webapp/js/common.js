$(document).ready(function(){
    $('#sideNav_close a').click(function() {
        $('#sidebar').removeClass('toggled');
        $('#header').removeClass('toggled');        
        $('#footer').removeClass('toggled');        
        $('#main').removeClass('toggled');        
    });
    $('#sideNav_open a').click(function() {
        $('#sidebar').addClass('toggled');
        $('#header').addClass('toggled');
        $('#footer').addClass('toggled');
        $('#main').addClass('toggled');
    });
});