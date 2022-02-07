/*$(function(){
			$(".l_r_move li").width($(window).width());
			$(".l_r_move ul").width($(".l_r_move li").width()*$(".l_r_move li").length);
			$(".l_r_move .li_conter").height($(".l_r_move ul").height());
			$(window).resize(function(){
				$(".l_r_move li").width($(window).width());
				$(".l_r_move ul").width($(".l_r_move li").width()*$(".l_r_move li").length);
				$(".l_r_move .li_conter").height($(".l_r_move ul").height());
			})
		
		
            
		})*/
		
        window.onload=function(){
            $(".l_r_move li").width($(window).width());
            $(".l_r_move ul").width($(".l_r_move li").width()*$(".l_r_move li").length);
            $(".l_r_move .li_conter").height($(".l_r_move ul").height());
            $(window).resize(function(){
                $(".l_r_move li").width($(window).width());
                $(".l_r_move ul").width($(".l_r_move li").width()*$(".l_r_move li").length);
                $(".l_r_move .li_conter").height($(".l_r_move ul").height());
            })
        
        
            
        }
        function leftRightMove(id){
            $("#"+id).find(".move_l_r").get(0).addEventListener('touchstart', touchSatrtFunc, false);  
            $("#"+id).find(".move_l_r").get(0).addEventListener('touchmove', touchMoveFunc, false);  
            $("#"+id).find(".move_l_r").get(0).addEventListener('touchend', touchEndFunc, false);
        }
		
            //全局变量，触摸开始位置  
            var startX = 0,text,left=0,xl=0;
              
            //touchstart事件  
            function touchSatrtFunc(evt) {  
                try  
                {  
                    //evt.preventDefault(); //阻止触摸时浏览器的缩放、滚动条滚动等  
  
                    var touch = evt.touches[0]; //获取第一个触点 
                    text=$(touch.target).parents(".l_r_move").find(".move_l_r");
                    var x = Number(touch.pageX); //页面触点X坐标  
                    //记录触点初始位置  
                    $(".move_l_r").stop(true,true)
                    startX = x;
					left=parseInt(text.css("left"));
  
                }  
                catch (e) {  
                    alert('touchSatrtFunc：' + e.message);  
                }  
            }  
  
            //touchmove事件，这个事件无法获取坐标  
            function touchMoveFunc(evt) {  
                try  
                {  
                    
                    var touch = evt.touches[0]; //获取第一个触点  
                    var x = Number(touch.pageX); //页面触点X坐标  
  
  
                    //判断滑动方向  
                    if (x - startX != 0) {
						xl=x - startX
						text.css("left",left+xl);
						
                        evt.preventDefault(); //阻止触摸时浏览器的缩放、滚动条滚动等   
                    }  
           
                }  
                catch (e) {  
                    alert('touchMoveFunc：' + e.message);  
                }  
            }  
  
            //touchend事件  
            function touchEndFunc(evt) {  
                try {  
                   // evt.preventDefault(); //阻止触摸时浏览器的缩放、滚动条滚动等  
  					if(xl>30){
						if(left!=0){
							text.animate({"left":left+$(".l_r_move li").width()},200);
							text.parents(".l_r_move").find(".bottom_btn").find("i").eq(text.parents(".l_r_move").find(".bottom_btn").find(".act").index()-1).addClass("act").siblings("i").removeClass("act");
						}else{
							text.animate({"left":left},200);
						}
					}else if(xl<-30){
						if(left!=-text.find("li").width()*(text.find("li").length-1)){
							text.animate({"left":left-text.find("li").width()},200);
							text.parents(".l_r_move").find(".bottom_btn").find("i").eq(text.parents(".l_r_move").find(".bottom_btn").find(".act").index()+1).addClass("act").siblings("i").removeClass("act");
						}else{
							text.animate({"left":-text.find("li").width()*(text.find("li").length-1)},200);
						}
						
					}
                }  
                catch (e) {  
                    alert('touchEndFunc：' + e.message);  
                }  
            }  
