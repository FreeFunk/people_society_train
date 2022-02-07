/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('registerCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
	    $scope.realNameObj = {
            failReason:''
        }
	    //身份证上传触发事件
        $scope.updateLoadIdCard = function(){
            $("#uploadIdCardImage").click();
        }

        //人脸上传触发事件
        $scope.updateLoadFacePhoto = function(){
            $("#uploadFaceImageUrl").click();
        }

        //学员实名认证
        $scope.studentSubmitRealName = function(){
            var studentPhoneNum = $("#studentPhoneNum").val();
            if(studentPhoneNum == null || studentPhoneNum == '' || !commonService.checkPhoneNum(studentPhoneNum)){
                $rootScope.alertError("手机号格式错误！");
                return ;
            }
            var password = $("#password").val();
            if(password == null || password == ''){
                $rootScope.alertError("请填写密码！");
                return ;
            }
            var conPassword = $("#conPassword").val();
            if(conPassword == null || conPassword == ''){
                $rootScope.alertError("请填写确认密码！");
                return ;
            }
            //判断两次输入的密码是否一致
            if(password != conPassword){
                $rootScope.alertError("两次输入的密码不一致！");
                return ;
            }
            var idCardImage = $('#idCardImage').val();
            if(!idCardImage || idCardImage==''){
                $rootScope.alertError("未上传身份证图片");
                return;
            }
            if(getOs()=='pc'){
                var base64Str = $scope.cutPicker();
                $("#faceImageUrl").val(base64Str.substring("data:image/jpeg;base64,".length));
            }else{
                var faceImageUrl = $("#faceImageUrl").val();
                if(!faceImageUrl || faceImageUrl==''){
                    $rootScope.alertError("未上传人脸拍照");
                    return;
                }
            }

            var url = "/stu/studentSubmitRealName.jsn";
            var param = $("#studentRealNameForm").serialize();
            $rootScope.request(url,param,function(data){
                if(data.success){
                    $scope.realNameObj.idCardImgPathUrl = data.data.idCardImgPath;
                    $scope.realNameObj.faceImageUrlUrl = data.data.faceImagePath;
                    //将身份证和人脸参数赋值
                    $("#idCardImageParam").val(data.data.idCardImgPath);
                    $("#faceImageUrlParam").val( data.data.faceImagePath);
                    //隐藏提交按钮
                    $scope.checkCount--;
                    $scope.realNameObj.failReason = '实名匹配中请勿切换页面...';

                    $timeout($scope.checkRealNameState,1500);
                }else{
                    $rootScope.errHttpAction(data);
                    $scope.checkCount = 10;
                    $scope.realNameObj.failReason = '';
                }
            },function(data){
                $("#faceImageUrl").val('');
                $("#faceImageUrlShow").attr("src","images/renlian.png");
                $rootScope.alertError(data.errMsg)
            });
        }
        $scope.checkCount = 10;
        /**
         * 检查人脸识别的人脸和身份证信息
         */
        $scope.checkRealNameState = function(){
            if($scope.checkCount<=0){
                $scope.checkCount=10;
                $scope.realNameObj.failReason = "";
                return;
            }
            $scope.checkCount--;
            var url = "/stu/checkStudentRegisterRealNameInfo.jsn";
            var param = $("#studentRealNameForm").serialize();
            $rootScope.request(url,param,function (data) {
                    var result = data.data;
                    var realNameState = result.state;
                    if(realNameState == 'already_real_name'){
                        $scope.alert("实名匹配成功",function(){
                            //学员登录一下
                            var studentPhoneNum = result.studentPhoneNum;
                            var password = result.password;
                            $scope.studentLogin(studentPhoneNum,password);
                        })
                    }
                    if(realNameState=='pass'){//成功

                        $scope.checkCount=10;
                        $scope.realNameObj.realNameState = realNameState;
                        $scope.realNameObj.failReason='实名匹配成功!';
                        $rootScope.alert('实名匹配成功!',function(){
                            //学员登录一下
                            var studentPhoneNum = $("#studentPhoneNum").val();
                            var password = $("#password").val();
                            $scope.studentLogin(studentPhoneNum,password);
                        });
                    }else if(realNameState=='wait'){//继续请求
                        $scope.realNameObj.failReason = '实名匹配中请勿切换页面...';
                        $timeout( $scope.checkRealNameState,3000);
                    }else if(realNameState=='notpass'){
                        $scope.realNameObj.failReason = result.failReason;
                        $scope.checkCount=10;
                    }
            },function (data) {
                $rootScope.alertError(data.errMsg);
            });

        }

        //用户登录
        $scope.studentLogin = function(phoneNum,password){
            var url = "/stu/studentLogin.jsn";
            if(isWx()){
                url = "/stu/studentLoginWx.jsn";
            }
            var param = "studentPhoneNum="+phoneNum+"&password="+password;
            $rootScope.request(url,param,function(data){
                if(data.success){
                    $rootScope.changePage("/main");
                }else{
                    $rootScope.errHttpAction(data);
                }
            });
        }

        /*********************** pc 端代码  begin*********************/

        $scope.initCamare = function(){

            var aVideo=document.getElementById('face_camare_vedio');
            $scope.camareVideo = aVideo;
            var aCanvas=document.getElementById('face_preview_canvas');
            $scope.aCanvas = aCanvas;
            $scope.canvasContext=aCanvas.getContext('2d');


            navigator.getUserMedia  = navigator.getUserMedia ||
                navigator.webkitGetUserMedia ||
                navigator.mozGetUserMedia ||
                navigator.msGetUserMedia;//获取媒体对象（这里指摄像头）
            navigator.getUserMedia({video:true}, gotStream, noStream);//参数1获取用户打开权限；参数二成功打开后调用，并传一个视频流对象，参数三打开失败后调用，传错误信息

            function gotStream(stream) {
                $scope.camareStream = stream;
                if("srcObject" in aVideo){
                    //新版浏览器
                    aVideo.srcObject  = stream;
                }else {
                    //兼容旧版浏览器
                    aVideo.src = window.URL.createObjectURL(stream);
                }
                aVideo.onerror = function () {
                    stream.stop();
                };
                stream.onended = noStream;
                aVideo.onloadedmetadata = function () {
                    //将图片展示到右下角 进行人离线断监测
                    $scope.takerealFaceInterval = setInterval(
                        function(){
                            var faceInput = $("#faceImageUrl");
                            var faceStr = faceInput.val();
                            if(!faceStr || faceStr.length==0){
                                var base64StrFace = $scope.cutPicker();
                                $("#faceImageUrlShow").prop("src",base64StrFace);
                                // faceInput.val(base64StrFace.substring("data:image/jpeg;base64,".length));
                            }


                        },300
                    );

                };
            }
            function noStream(err) {
                alert(err);
            }
        }

        $scope.cutPicker = function(){
            $scope.canvasContext.drawImage($scope.camareVideo, 0, 0, 520, 400);
            var base64StrFace = $scope.aCanvas.toDataURL("image/jpeg");
            return base64StrFace;
        }

        if(getOs()=='pc'){//如果是pc那么启动摄像头
            $timeout(function(){
                $scope.initCamare();
            },300);
        }



        /*********************** pc 端代码  end*********************/
        //scope销毁时的监听
        $scope.$on('$destroy', function() {
            try{
                //销毁摄像头调用
                var stream = $scope.camareStream;
                if(stream && stream.getTracks){
                    var tracks = stream.getTracks();//[0].stop()
                    for(i=0;i<tracks.length;i++){
                        var track = tracks[i];
                        if(track.stop){
                            try {
                                track.stop();
                            }catch (e){}
                        }
                    }
                }
            }catch(e){}

        });

	}]);
});