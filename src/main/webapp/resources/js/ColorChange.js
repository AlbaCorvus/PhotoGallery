$(function(){

var colorList=document.getElementById("color-choice");

function changeColors(e){
var target = e.getTarget || e.srcElement;

var parentElement = target.parentNode;
console.log(target.value);
$(parentElement).children("h2").css({'color':target.value});
$(parentElement).children("input[type=submit]").css({'background-color':target.value});

}

if(colorList.addEventListener){
colorList.addEventListener('click',function(e){
changeColors(e);
},false);
}else{
colorList.attachEvent('onclick',function(e){
changeColors(e);
});
}

});