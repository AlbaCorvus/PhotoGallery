$(function(){

var bigImage= document.getElementsByClassName("big-image-display")[0];
var $images=$('.image');

$(document).on('click','.image',function(e){

$images.removeClass('alive');



$(this).addClass('alive');
$(bigImage).css({'background-image':'url('+$(this).children().attr('src')+')'});
});


$('.image').eq(0).click();
});