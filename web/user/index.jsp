<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
%>
<html lang="en">
<head>
    <title>小米首页</title>
    <link rel="stylesheet" href="<%=basePath %>css/index.css">
    <script src="<%=basePath %>js/jquery.1.11.1.min.js"></script>
    <style>

    </style>

</head>
<body style="background-color: #fafafa;">
    
    <!-- 头部分 -->
    <jsp:include page="header.jsp"></jsp:include>
    
    <!-- 滚动图片 -->
    <div class="scroll">
        <ul>
            <li><a href=""><img width="1226px" height="460px" src="<%=basePath %>img/j11.jpg" alt=""></a></li>
            <li><a href=""><img width="1226px" height="460px" src="<%=basePath %>img/j22.jpg" alt=""></a></li>
            <li><a href=""><img width="1226px" height="460px" src="<%=basePath %>img/j33.jpg" alt=""></a></li>
            <li><a href=""><img width="1226px" height="460px" src="<%=basePath %>img/j44.jpg" alt=""></a></li>
            <li><a href=""><img width="1226px" height="460px" src="<%=basePath %>img/j55.jpg" alt=""></a></li>
            <li><a href=""><img width="1226px" height="460px" src="<%=basePath %>img/j66.jpg" alt=""></a></li>
        </ul>
        <div class="scroll_dot">
            <span class="scroll_dot_span"></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
        </div>
        <div class="scroll_arrows">
            <a href="javascript:void(0);"><span class="left scroll_arrows_back">〈</span></a>
            <a href="javascript:void(0);"><span class="right scroll_arrows_back">〉</span></a>
        </div>
        <div class="scroll_left" style="height: 420px;">
            <ul class="scr-ul">
            	
            	<c:forEach items="${categoryList }" var="category">
            		<li class="scr_li"><a href="#">${category.name }</a><i class="scr_i"></i></li>
            	</c:forEach>
                
            </ul>
        </div>
        
    </div>
   
    <div class="time">
        <div class="H">小米热销</div>
        <div class="time_in">
	          
	          <c:forEach items="${HKHTCommodityList }" var="hkht">
		          <div style="background-color: #fff;width: 234px;height:320px;float: left;margin-left: 11px; ">
		            	<a href="<%=basePath %>IndexServlet?mark=showCommodityById&cid=${hkht.cid}" target="_blank">
		            		<img class="time_min" style="width:234px;height: 234px;" src="http://localhost:8080/${hkht.pic }" alt="">
		            	</a>
		            	<div style="clear: both;"></div>
		            	
		            	<div style="width: 234px;height: 85px;">
		            		<div style="width: 234px;height: 20px;line-height: 20px;text-align: center;font-size: 14px;font-family: Arial">
		            			${hkht.name }
		            		</div>
		            		<div style="width: 234px;height: 30px;line-height: 30px;text-align: center;color: #ff6700;font-size: 14px;font-family: Arial">
		            			 ￥ ${hkht.price } <span style="color: #000"> &nbsp;起始价 </span>
		            		</div>
		            		<div style="width: 234px;height: 35px;line-height: 35px;text-align: center;color: #000;font-size: 14px;font-family: Arial" title="${hkht.description}">
		            			
		            			<c:if test="${hkht.description.length() <=12 }">
		            				${hkht.description}
		            			</c:if>
		            			<c:if test="${hkht.description.length() > 12 }">
		            				${fn:substring(hkht.description ,0,12)}...
		            			</c:if>
		            		</div>
		            	</div>
		            </div>
	           </c:forEach>
        </div>
    </div>
    <div class="appliances">
        <div class="app_width" style="padding-top: 0px">
            <div class="app_A">手机</div>
            <div class="app_Ar orangeGL">
              
            </div>
            <div class="app_max">
                <div class="appl"><a href=""><img width="233px" height="615px" src="<%=basePath %>img/22222.png" alt=""></a></div>
                <div class="appr">
                    <div class="appr_top">
                    	<!-- 循环后台取到的家电的集合 -->
                    		
                    	<c:forEach items="${WGMJCommodityList }" var="wgmj" varStatus="i">
                    		
                    		<c:if test="${i.count <=4 }">
	                  			<div class="appr_min" id="appr_min1" style="margin-left: 12px;width: 234px;height: 300px;background-color: #FFF;text-align: center;">
		                  			<a href="<%=basePath %>IndexServlet?mark=showCommodityById&cid=${wgmj.cid}" target="_blank"><img width="180px" height="180px;" src="http://localhost:8080/${wgmj.	pic }" alt=""></a>
		                  			
		                  			<div style="width: 234px;height: 100px;">
		                  				<div style="width: 100%;height: 33px;text-align: center;font-size: 14px;font-family: Arial">${wgmj.name }</div>
		                  				<div style="width: 100%;height: 33px;text-align: center;font-size: 14px;font-family: Arial">${wgmj.description }</div>	
		                  				<div style="width: 100%;height: 33px;text-align: center;color: #ff6700;font-size: 14px;font-family: Arial">${wgmj.price }</div>
		                  			</div>
		                  		</div>
                  			</c:if>
                  			<c:if test="${i.count > 4 }">
	                  			<div class="appr_min" style="margin-left: 12px;width: 234px;height: 300px;background-color: #FFF;text-align: center;margin-top: 15px;" id="appr_min1">
	                  			<a href="<%=basePath %>IndexServlet?mark=showCommodityById&cid=${wgmj.cid}" target="_blank"><img width="180px" height="180px;" src="http://localhost:8080/${wgmj.pic }" alt=""></a>
	                  			<div style="width: 234px;height: 100px;">
	                  				<div style="width: 100%;height: 33px;text-align: center;font-size: 14px;font-family: Arial">${wgmj.name }</div>
	                  				<div style="width: 100%;height: 33px;text-align: center;font-size: 14px;font-family: Arial">${wgmj.description }</div>	
	                  				<div style="width: 100%;height: 33px;text-align: center;color: #ff6700;font-size: 14px;font-family: Arial">${wgmj.price }</div>
	                  			</div>
	                  			</div> 
                  			</c:if>
                   		</c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>`
    <div class="capabackground">
        <div class="capacity" style="padding-top: 0px">
            <div class="capa_box_top">
                <div class="capa_box_top_al">智能</div>
                <div class="capa_box_top_ar">
                </div>
            </div>
            	<div class="capa_box scrip_capa_box scrip_capa_box_on">
                	<div class="capa_top">
                	
                    	<!-- 军火库  -->
                    	<c:forEach items="${JHKCommodityList }" var="jhk">
	                   		<div class="capa capa_shadow" style="width: 231px;height: 301px;text-align: center;background-color: #FFFFFF;margin-top: 15px;">
		                        <a href="<%=basePath %>IndexServlet?mark=showCommodityById&cid=${jhk.cid}" target="_blank"><img width="180px" height="180px" src="http://localhost:8080/${jhk.pic }">
		                            <div class="tran">
		                                <span>${jhk.full_description }</span>
		                            </div>
		                        </a>
		                        <div class="capa_a">
		                            <ul>
		                                <li style="color: #333333">${jhk.name } </li>
		                                <li style="color: #b0b0b0">${jhk.description }</li>
		                                <li style="font-size: 14px;margin-top: 10px; color: #ff6700" >${jhk.price } 元</li>
		                            </ul>
		                        </div>
		                    </div>
	                    </c:forEach>
                    </div>
               </div>

            </div>
    </div>
    <div class="recommend">
        <div class="capacity">
            <div class="capa_box_top">
                <div class="capa_box_top_al">明星单品</div>
                <div class="capa_box_top_ar">
                </div>
            </div>
            <div class="capa_box">
                <div class="capa_top">
					
					<!-- 动态数据使用，仅供参考 -->
					<c:forEach items="${MXDPCommodityList }" var="mxdp">
	                	<div class="capa capa_shadow" style="margin-right: 10px;background-color: #fff">
	                        <a href="<%=basePath %>IndexServlet?mark=showCommodityById&cid=${mxdp.cid}" target="_blank"><img width="234"  src="http://localhost:8080/${mxdp.pic}">
	                           
	                        </a>
	                        <div class="capa_a">
	                            <ul>
	                                <li style="color: #333333"> ${mxdp.name } </li>
	                                <li style="color: #b0b0b0"> ${mxdp.description } </li>
	                                <li style="font-size: 14px;margin-top: 10px; color: #ff6700" >${mxdp.price } 元</li>
	                            </ul>
	                        </div>
	                    </div>
                	</c:forEach>

                </div>


            </div>

        </div>
    </div>
    <div class="popular">
        <div class="popular_background">
            <div class="popular_box_top">
                <div class="popular_box_top_al">热门产品</div>

            </div>
            <div class="popu_box">
            	
            	<!-- 动态数据使用，仅供参考 -->
            	<c:forEach items="${RMCommodityList }" var="rm" varStatus="i">
            		
            		<c:if test="${i.count ==1 }">
            			<div class="popu popu_unleft popu_shadow" >
	                        <a href="<%=basePath %>IndexServlet?mark=showCommodityById&cid=${rm.cid}" target="_blank"><img style="width: 296px" height="220px" src="http://localhost:8080/${rm.pic}"></a>
		                        <div class="popu_bottom">
		                            <p class="review">
		                                	${rm.full_description }
		                            </p>
		                            <p class="author"> 来自于 秘密 的评价 </p>
		                            <div class="info">
		                                <h3 class="title">${rm.name }</h3>
		                                <span class="sep">|</span>
		                                <p class="price"><span>${rm.price }</span>元</p>
		                            </div>
		                        </div>
		                  </div>
            		</c:if>
            		<c:if test="${i.count !=1 }">
            			<div class="popu popu_unleft popu_shadow" style="margin-left: 13px;">
	                        <a href="<%=basePath %>IndexServlet?mark=showCommodityById&cid=${rm.cid}" target="_blank"><img style="width: 296px" height="220px" src="http://localhost:8080/${rm.pic}"></a>
		                        <div class="popu_bottom">
		                            <p class="review">
		                                	${rm.full_description }
		                            </p>
		                            <p class="author"> 来自于 秘密 的评价 </p>
		                            <div class="info">
		                                <h3 class="title">${rm.name }</h3>
		                                <span class="sep">|</span>
		                                <p class="price"><span>${rm.price }</span>元</p>
		                            </div>
		                        </div>
		                  </div>
            		</c:if>
	                
	              </c:forEach>
	              
            </div>
        </div>
    </div>
   
    <div class="video">
        <div class="popular_background">
            <div class="popular_box_top">
                <div class="popular_box_top_al">视频</div>

            </div>
            <div class="popu_box">
                <div class="vid popu_unleft popu_shadow">
                    <div class="video_top">
                        <a href=""><video class="this_vid" src="<%=basePath %>img/snowPerson.mp4" controls loop  poster="<%=basePath %>img/video_01.jpg"></video></a>
                    </div>
                    <div class="video_bottom">
                        <h3 class="vid_title"><a href="">小米8，一部与众不同的手机</a></h3>
                        <p class="vid_desc">透明探索版，将科技与美学完美结合</p>
                    </div>
                </div>
                <div class="vid  popu_shadow">
                    <div class="video_top">
                        <a href=""><video class="this_vid" src="<%=basePath %>img/snowPerson.mp4" controls loop  poster="<%=basePath %>img/video_02.jpg"></video></a>
                    </div>
                    <div class="video_bottom">
                        <h3 class="vid_title"><a href="">小米MIX 2S，一面科技 一面艺术</a></h3>
                        <p class="vid_desc">艺术品般陶瓷机身，惊艳、璀璨</p>
                    </div>
                </div>
                <div class="vid  popu_shadow">
                    <div class="video_top">
                        <a href=""><video class="this_vid" src="<%=basePath %>img/snowPerson.mp4" controls loop  poster="<%=basePath %>img/video_03.jpg"></video></a>
                    </div>
                    <div class="video_bottom">
                        <h3 class="vid_title"><a href="">天生丽质的小米6X</a></h3>
                        <p class="vid_desc">让你一见倾心</p>
                    </div>
                </div>
                <div class="vid  popu_shadow">
                    <div class="video_top">
                        <a href=""><video class="this_vid" src="<%=basePath %>img/snowPerson.mp4" controls loop  poster="<%=basePath %>img/video_01.jpg"></video></a>
                    </div>
                    <div class="video_bottom">
                        <h3 class="vid_title"><a href="">生活中无所不在的小爱同学</a></h3>
                        <p class="vid_desc">透明探索版，将科技与美学完美结合</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 底部的包含 -->
	<jsp:include page="footer.jsp"></jsp:include>

    <script>
        var abc = document.getElementsByClassName("script_capa_box_top_ar");
        console.log("abc:"+abc);
        console.log("abc[0]"+abc[0]);
        var box = document.getElementsByClassName("scrip_capa_box");
        console.log("box:"+box);
        console.log("box[0]:"+box[0]);
        for(var i = 0; i<abc.length;i++){
            abc[i].index=i;
            abc[i].onmouseover=function(){
                for(var j = 0 ;j<abc.length;j++){
                    box[j].className="capa_box scrip_capa_box";
                }
                box[this.index].className="capa_box scrip_capa_box scrip_capa_box_on"
                console.log("this.index:"+this.index);
            }
        }
        var n = 0;

        /*setInterval(function () {
            n++;
             if(n > $(".scroll ul li").length){
             n=0;
             }
            $(".scroll ul li").css("opacity","0").eq(n).css("opacity","1")
        },100)*/
        var t = setInterval(fun,1000);
        function  fun() {
            n++;
            if(n > $(".scroll>ul>li").length-1){
                n = 0;
            }
            $(".scroll>ul>li").css("opacity","0").eq(n).css("opacity","1")
            $(".scroll_dot span").eq(n).addClass("scroll_dot_span").siblings().removeClass("scroll_dot_span");
        }
        $(".scroll_arrows .left").click(function() {
            n -= 2;
                    if(n <-1){
                        n=4;
                    }
                    fun()

        } );
        $(".scroll_arrows .right").click(function() {
            fun()
        });
        $(".scroll_dot span").click(function () {
            console.log($(this).index());
            n=$(this).index()-1;
            $(this).siblings().removeClass("scroll_dot_span").end().addClass("scroll_dot_span");
            fun()
        }); $(".scroll").hover(function () {
                    clearInterval(t);
                },
                function () {
                    t = setInterval(fun,1000);
                });

    </script>
</body>
</html>