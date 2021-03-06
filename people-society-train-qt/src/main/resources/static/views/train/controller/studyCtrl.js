/**
 *  首页
 */
define(['app','commonService','ngInfiniteScroll'], function(app){
	return app.controller('studyCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams','$interval','$timeout','$window',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams,$interval,$timeout,$window) {
	    //是否显示练习题的解析
	    $scope.showPractiseFenxi = appConfig.showPractiseFenxi;
	    $scope.showPractiseFenxiFlag = false;
        $scope.toglePractiseFenxi = function(){
            $scope.showPractiseFenxiFlag = !$scope.showPractiseFenxiFlag;
        }
	    //视频的播放状态
        $scope.videoPlayState = false;
		//默认显示tab页面
		$scope.currTab = 'chapter';
		//URL参数中的当前学习的课程
        $scope.stuCourseId = $routeParams.stuCourseId;
        //当前课程
        $scope.curStuCourse = {};
        //当前手机机型
        $scope.phoneTypeFlag = '';

        /**
         * 加载课程
         * @param stuCourseId
         */
        $scope.loadCourse = function (stuCourseId) {

            var url = "/stuCourse/loadCourse.jsn";
            var param ='stuCourseId=' + stuCourseId;
            $rootScope.request(url,param,function(data){
                $scope.phoneTypeFlag = data.data.phoneTypeFlag;
                $scope.curStuCourse = data.data;
                $scope.prepareStudy();
                var courseImage = $scope.curStuCourse.courseImage;
                if(courseImage && courseImage.indexOf('http')!=0){
                    courseImage = appConfig.baseImgUrl + courseImage;
                    $scope.curStuCourse.courseImage = courseImage;
                }
                //设置封面
                $("#id_study_video").prop("poster" , $scope.curStuCourse.courseImage);
            });
        }



        /**
         * 准备播放学习
         * 1.设置各个视频的学习进度
         * 2.设置当前正在学习
         * 3.根据上次记录的播放章节设置当前正在播放
         * 4.根据章节记录的上次播放位置进行播放
         * 5.请求播放限制（是否有生效的人脸记录）
         * 6.如果没有人脸需要调起人脸签到
         */
        $scope.prepareStudy = function(){
            //没有课程就返回吧
            if($scope.curStuCourse.courseNodes==null||$scope.curStuCourse.courseNodes.length==0){
                return;
            }
            var cousresNodeMap = {};
            var lastLearnNodeId = $scope.curStuCourse.lastLearnNodeId;
            var tempCurrentNode = null;
            //设置各个视频的学习进度
            var preLearnNode = null;
            for(i=0;i<$scope.curStuCourse.courseNodes.length;i++){
                var courseNode = $scope.curStuCourse.courseNodes[i];
                if(preLearnNode==null){
                    preLearnNode = courseNode;
                }else{
                    preLearnNode.nextNode = courseNode;
                    courseNode.preLearnNode = preLearnNode;
                    preLearnNode = courseNode;
                }

                if(lastLearnNodeId!=null && courseNode.id ==lastLearnNodeId){
                    tempCurrentNode = courseNode;
                }
                cousresNodeMap[courseNode.id] = courseNode;
                //计算时长显示
                var nodeTimeLength = courseNode.nodeTimeLength;
                if(nodeTimeLength!=null){
                    //将秒数转换成分:秒
                    courseNode.nodeTimeLengthStr = $rootScope.changeSecondToStr(nodeTimeLength);
                }
            }
            angular.forEach($scope.curStuCourse.stuNodes,
                function(stuNode,index,array){
                   var courseNode = cousresNodeMap[stuNode.nodeId];
                   if(courseNode==null){return};
                   courseNode.nodeProgress = stuNode.nodeProgress;
                   /*if(courseNode.nodeProgress && courseNode.nodeProgress>0){
                       courseNode.nodeProgressStr = (courseNode.nodeProgress*100)+"%";
                   }*/
                   courseNode.lastLearnLocation = stuNode.lastLearnLocation;
                   courseNode.learnIsFinished = stuNode.learnIsFinished;
                   courseNode.stuCourseId = stuNode.ownerStudentAndCourseId;
                   courseNode.questionIsFinished = stuNode.questionIsFinished;
                   courseNode.nodeQuestionScore = stuNode.nodeQuestionScore;
                   courseNode.questionIsPass = stuNode.questionIsPass;
                   courseNode.studyTimeLength = stuNode.studyTimeLength;
                   if(courseNode.studyTimeLength==null){
                       courseNode.studyTimeLength = 0;
                   }
                   courseNode.studyTimeLengthStr = $rootScope.changeSecondToStr(courseNode.studyTimeLength);
                   courseNode.stuNodeId = stuNode.id;
                   courseNode.maxLearnLocation = stuNode.maxLearnLocation;
                   if(courseNode.maxLearnLocation==null){
                       stuNode.maxLearnLocation=0;
                   }
                }
            );
            //2.设置当前正在学习-如果没有上次播放的章节那么从第一节开始
            if(lastLearnNodeId==null || tempCurrentNode==null){
                tempCurrentNode = $scope.curStuCourse.courseNodes[0];
            }
            $scope.freshLearnNode(tempCurrentNode,true);
            $scope.doPlayCourseNode(tempCurrentNode);
            if(tempCurrentNode.lastLearnLocation>0){
                $scope.setSeekHistorySecond(tempCurrentNode.lastLearnLocation);
            }

        }



        /**
         *  播放课程某个章节
         *  1.判断是否有人脸验证
         *  2.播放并且跳到指定位置
         * @param courseNode
         * @param isBegin 是否是切换播放或者第一次播放，如果第一次播放后台不计时
         */
        $scope.freshLearnNode = function(courseNode,isBegin){
            var isChangeCourse = false;
            if($scope.curStuCourse.currentNode != courseNode){
                isChangeCourse = true;
            }
            var begin = false;
            if(isBegin){begin = true }
            $scope.curStuCourse.currentNode = courseNode;
            var lastLearnLocation = $scope.curStuCourse.currentNode.lastLearnLocation;
            try{
                if(lastLearnLocation == null){
                    lastLearnLocation = 0;
                }else{
                    lastLearnLocation = parseInt(lastLearnLocation);
                }
                if(isNaN(lastLearnLocation)){lastLearnLocation=0;}
            }catch (e){
                lastLearnLocation = 0;
            }
            var url = '/stuCourse/freshLearnNode_v1.jsn';
            var param = 'courseNodeId='+ courseNode.id +
                        "&stuCourseId=" + $scope.stuCourseId +
                        "&isBegin=" + begin +
                        "&lastLearnLocation=" + lastLearnLocation ;

            $rootScope.request(url,param,
                function(data){//成功回调
                    var stuNode = data.data;
                    var stuNodeId  = stuNode.id;
                    //设置学员的学习记录
                    courseNode.stuNodeId = stuNodeId;
                    $scope.practiseObj.stuNodeId = stuNodeId;

                    //最大观看时间
                    if(courseNode.maxLearnLocation<stuNode.maxLearnLocation){
                        courseNode.maxLearnLocation = stuNode.maxLearnLocation;
                    }

                    courseNode.studyTimeLength = stuNode.studyTimeLength;
                    if(courseNode.studyTimeLength==null){
                        courseNode.studyTimeLength = 0;
                    }
                    courseNode.studyTimeLengthStr = $rootScope.changeSecondToStr(courseNode.studyTimeLength);
                    if(isChangeCourse){
                        $scope.doPlayCourseNode(courseNode);
                        $scope.setSeekHistorySecond(lastLearnLocation)
                    }
                    
                    if(isBegin){
                        var needFace = $scope.curStuCourse.courseIsNeedFaceContrast;
                        if(needFace!='0'){
                            $scope.callFaceCheckUp(function(){
                                //人脸通过之后播放
                                // $scope.doPlayCourseNode(courseNode);
                            });
                        }
                    }
                },
                function(data){//失败回调
                    if(data.errCode=='noLearnFace'){
                        $scope.callFaceCheckUp(function(){
                            //人脸通过之后播放
                            // $scope.doPlayCourseNode(courseNode);
                        });
                    }else if(data.errCode=='course_reach_learn_limit'){
                        $rootScope.alert(data.errMsg,function(){
                            $rootScope.changePage("/courseList");
                        });
                        $scope.pauseVideoForFace();
                    }else if(data.errCode=='cur_node_num_overflow'){
                        $rootScope.alert(data.errMsg,function(){
                            $rootScope.changePage("/courseList");
                        });
                        $scope.pauseVideoForFace();
                    }else{
                        $rootScope.alert('网络异常',function(){
                            $rootScope.changePage("/courseList");
                        });
                        $scope.pauseVideoForFace();
                    }
                }
            );
        }

        /**
         * 确认要播放 - 此方法用于安卓人脸之后初始化，第一次初始化等场景
         * 1.根据历史记载位置播放
         * 2.调用开始播放
         */
        $scope.doPlayCourseNode = function(courseNode,notPlayFlag){
            var jqVideo = $("#id_study_video");
            var video = jqVideo[0];
            jqVideo.prop("src" , courseNode.fileUrl);


            $scope.initPlayer();
            $scope.freshPlayerSeekState(courseNode.learnIsFinished=='1');
            if(!notPlayFlag){
                $scope.playVideo();
            }
            var seekNum = courseNode.lastLearnLocation;
            if(seekNum && seekNum>0){
                 $scope.seekVideo(seekNum,true);
            }

        }

        $scope.loadCourse($scope.stuCourseId);

		//刷新不允许
		$scope.freshPlayerSeekState = function(isAllow){
		    if($scope.disableSeekInterval){
                clearInterval($scope.disableSeekInterval);
            }

            // 禁止快进
            $scope.vedioLastTime;
            var video = $("#id_study_video")[0];
            $scope.videoPlay = video;
            var disableSeekInterval = setInterval(function () {
                var crtime = video.currentTime;
                //如果上次和本次的时间是一样的那么可以设置全局播放状态为未播放
                if($scope.vedioLastTime==video.currentTime){
                    $scope.videoPlayState = false;
                }
                //TODO:更新当前的播放位置,暂时没用我先注释掉
                /*if($scope.videoPlayState){
                     // $scope.curStuCourse.currentNode.lastLearnLocation = video.currentTime;
                }else{
                    return;
                }*/

                if(isAllow){
                    return;
                }


                if($scope.vedioLastTime &&(crtime-$scope.vedioLastTime>5)){
                    //如果再最大观看范围内允许快进
                    var maxLocTime = $scope.vedioLastTime;
                    try{

                        maxLocTime = $scope.curStuCourse.currentNode.maxLearnLocation;
                    }catch(e){
                    }
                    if(!maxLocTime){

                        video.currentTime = $scope.vedioLastTime;
                    }else if(crtime>maxLocTime){
                        video.currentTime = maxLocTime;
                    }
                }
                $scope.vedioLastTime = video.currentTime;
                if($scope.curStuCourse.currentNode&&$scope.curStuCourse.currentNode.maxLearnLocation!=null){
                    if($scope.curStuCourse.currentNode.maxLearnLocation<video.currentTime){
                        $scope.curStuCourse.currentNode.maxLearnLocation = video.currentTime;
                    }
                }

            },400);
            $scope.disableSeekInterval = disableSeekInterval;

		}


		// 重启计时的任务 $scope.timeCountInterval
		$scope.restartTimeCountJob = function(){
            if($scope.timeCountInterval){
                clearInterval($scope.timeCountInterval);
            }
            if($scope.timeCountTimeout){
                clearTimeout($scope.timeCountTimeout);
            }


            //请求后台更新学习情况 setInterval的方式有误差
            $scope.timeCountInterval = setInterval(function(){
                // $rootScope.alertError($scope.videoPlayState);
                if($scope.videoPlayState){
                    var video = $("#id_study_video")[0];
                    $scope.curStuCourse.currentNode.lastLearnLocation = video.currentTime;
                    //刷新时间
                    $scope.freshLearnNode($scope.curStuCourse.currentNode,false);
                }
            },60000);




        }
        //先调起来先尝尝
        $scope.restartTimeCountJob();
        $scope.submitLearnDone = function(){
            var url = "/stuCourse/curNodePlayDone.jsn";

            var param = 'stuCourseId=' + $scope.curStuCourse.id;
            param += '&courseNodeId=' + $scope.curStuCourse.currentNode.id
            $rootScope.request(
                url,param,
                function(data){
                    $scope.playDoneDealing = false;
                    var temStuNode = data.data;
                    //设置当前视频是否完成播放
                    $scope.curStuCourse.currentNode.learnIsFinished='1';
                    $scope.curStuCourse.currentNode.lastLearnLocation=0;
                    //成功之后调用播放下一个
                    $scope.curStuCourse.currentNode.learnIsFinished='1';
                    $scope.curStuCourse.currentNode.nodeProgress=100;
                    $scope.curStuCourse.currentNode.questionIsFinished = temStuNode.questionIsFinished;
                    $scope.curStuCourse.currentNode.questionIsPass = temStuNode.questionIsPass;
                    //判断去答题
                    if($scope.curStuCourse.currentNode.questionIsPass=='1'){
                        var nextNode = $scope.curStuCourse.currentNode.nextNode;
                        if(nextNode!=null){
                            $rootScope.alertConfirm('本节学完是否学习下一节?',function(btn){
                                if(btn){
                                    $scope.freshLearnNode(nextNode,true);
                                    $scope.doPlayCourseNode(nextNode);
                                    if(nextNode.lastLearnLocation>0){
                                        $scope.setSeekHistorySecond(nextNode.lastLearnLocation);
                                    }
                                }

                            });
                        }
                    }else{

                        //请他去答题
                        $rootScope.alert('本节播放结束，请完成课后练习！',function(btn){
                            $scope.currTab = 'practise';
                        });
                    }
                    $scope.closeFaceCheck();
                },
                function(data){
                    $scope.playDoneDealing = false;
                    //失败回调
                    if(data.errCode=='noLearnFace'){
                        $scope.callFaceCheckUp(function(){
                            //人脸通过之后继续调用结束
                            $scope.curNodePlayDone();
                        },'notplay');
                    }else if(data.errCode=='face_num_not_enough'){
                        $rootScope.alert(data.errMsg,function(){
                            $scope.playCourseNode($scope.curStuCourse.currentNode);
                        });
                    }else{
                        if(data.errMsg == '课程学习时长不足请继续学习'){

                            $rootScope.alert(data.errMsg,function(){
                                //再播放视频
                                $scope.playCourseNode($scope.curStuCourse.currentNode);
                                //跳转到上次播放位置
                                //这里的这个操作只有安卓手机在人脸识别之后才有用
                                var sec = $scope.curStuCourse.currentNode.studyTimeLength;
                                //并且要往回反
                                setTimeout(function(){
                                    $scope.seekVideo(sec,true);
                                },2000);

                            });

                        }else{
                            $rootScope.alert(data.errMsg);
                        }

                    }
                }
            );
        }

		$scope.playDoneDealing = false;
        /**
         * 学习到达末尾更新课程学习已经完成
         * 1.将课程设置为已经完结调用后台方法
         * 2.修改前台scope中的对象的值
         */
		$scope.curNodePlayDone = function(){
		    if($scope.playDoneDealing){
                return;
            }
            $scope.playDoneDealing = true;

            var needFace = $scope.curStuCourse.courseIsNeedFaceContrast;
            if(needFace!='0'){
                $scope.callFaceCheckUp($scope.submitLearnDone,'notplay');
            }else{
                //不需要人脸的课程也要结束课程
                $scope.submitLearnDone();
            }


        }

        $scope.initPlayer = function(){
            // 禁止快进
            var jqVideo = $("#id_study_video");
            var video = jqVideo[0];
            video.load();
            video.addEventListener("pause",function(){
                $scope.videoPlayState = false;
            });
            //播放时候时间会动
            video.addEventListener('timeupdate', function(e) {
                if(video.currentTime==0){
                    //如果是开头那么未播放
                    $scope.videoPlayState = false;
                }else{
                    $scope.videoPlayState = true;
                }
            })


            video.addEventListener("ended", function() {
                try{
                    $scope.exitFullScreen();
                }catch (e){}
                //将课程设置为已经完结调用后台方法修改前台scope中的对象的值
                $scope.curNodePlayDone();

            });


            //清理计时重置计时任务
            $scope.restartTimeCountJob();
        }

        //退出全屏
        $scope.exitFullScreen = function(){
            //退出全屏
            var doc = document;
            if (doc.exitFullscreen) {
                try{
                    doc.exitFullscreen();
                }catch (e){}
            }
            if (doc.mozCancelFullScreen) {
                try {
                    doc.mozCancelFullScreen();
                }catch (e){}
            }
            if (doc.webkitCancelFullScreen) {
                try {
                    doc.webkitCancelFullScreen();
                }catch (e){}
            }
            var jqVideo = $("#id_study_video");
            var video = jqVideo[0];
            if(video.webkitExitFullScreen){
                try {
                    video.webkitExitFullScreen();
                }catch (e){}
            }
            if(getOs()=='android'&& isWx()){
                //安卓微信中退出全屏
                video.srcObject = new window.webkitMediaStream;

                $("#id_study_video_con").empty();

                setTimeout(function () {
                    var src = $scope.curStuCourse.currentNode.fileUrl;
                    //解决中间过程刷脸不播放的问题
                    src = src + "?t=" + new Date().getTime();
                    var videoHtml = "<video id=\"id_study_video\"\n" +
                        "                    style=\"width: 100%;height: 225px;object-fit:fill;\"\n" +
                        // "                    controls\n" +
                        "                    src=\"" + src + "\""+
                        "                    x5-video-player-type=\"h5-page\"\n" +
                        "                    x5-video-player-fullscreen=\"true\"\n" +
                        "                    webkit-playsinline=\"true\"\n" +
                        "                    x-webkit-airplay=\"true\"\n" +
                        "                    playsinline=\"true\"\n" +
                        "                        >\n" +
                        "                        </video>";
                    $("#id_study_video_con").html(videoHtml);
                    $scope.initPlayer();
                    var poster = $scope.curStuCourse.currentNode.imageUrl;
                    //设置封面
                    $("#id_study_video").prop("poster" ,poster);
                    $scope.doPlayCourseNode($scope.curStuCourse.currentNode,true);
                    video.pause();
                },100);
            }
        }


		$scope.faceCheckObj = {
		    checking:false,
            successCall:null,
            checkFaceTimes:20,
            faceState:'notpass',
            faceToken:null
        };
        if(getOs()=='pc'){
            $scope.faceCheckObj.checkFaceTimes = 8;
        }else{
            $scope.faceCheckObj.checkFaceTimes = 20;
        }

        $scope.closeFaceCheck = function(){
            //
            $scope.faceCheckObj.checking = false;
            $("#face_check_container").hide();
            //关闭web端的人脸检测窗口
            $("#face_check_container_web").hide();

            $scope.faceCheckObj.faceState = 'notpass';
            $scope.faceCheckObj.successCall = null;
            $scope.faceCheckObj.playVideoFlag = 'play';
            debugger;
            if(getOs()=='pc'){
                $scope.faceCheckObj.checkFaceTimes = 8;
            }else{
                $scope.faceCheckObj.checkFaceTimes = 20;
            }

            $scope.faceCheckObj.faceState = 'notpass';
            $scope.faceCheckObj.faceToken = null;
            //设置显示图片
            $("#face_photoShow").prop("src","images/timg.png");
            $scope.togleFaceErr();

        }

        /**
         * 根据当前手机机型动态切换摄像头的调起方法
         * @param curStuCourse
         */
        $scope.changePhoneType = function(curStuCourse){
            var OsObject = navigator.userAgent.toLocaleLowerCase();
            console.log("调试信息:"+OsObject);
            //获取build 机型
            if (OsObject.indexOf('build')!=-1){
                var str = OsObject.substring(OsObject.indexOf('build/'),OsObject.indexOf(')'));
                var phoneType = str.split("/")[1];
                console.log(phoneType);
                //获取到手机机型 配置
                // debugger
                var studentId = curStuCourse.studentId;
                var url = "/stu/updateStuPhoneType.jsn";
                //提交人脸到后台
                var param = 'stuId=' + studentId;
                $rootScope.request(url,param,
                    function(data){//成功回调
                        //并且要判断是否已经存在的机型
                        //刷新当前页面
                        // $scope.phoneTypeFlag = data.data;
                        // $scope.loadCourse($routeParams.stuCourseId);
                        $window.location.reload();
                    },function (data) {
                        $rootScope.alert('请重新尝试！');
                    }
                );
            }

        }



        /**
         * 调起人脸识别
         */
		$scope.callFaceCheckUp   = function(callBack,playVideoFlag){
		    if(getOs()=='pc' && isWx()){
		        $rootScope.alert('电脑端推荐谷歌浏览器学习!');
            }
		    //pc 端进web
		    if(isH5Camera()&&!isWx()||isSepcielPhone($scope.phoneTypeFlag) || getOs()=='pc'){
		        //TODO是否开启人脸线断的开关
                if($rootScope.globalSchObj && $rootScope.globalSchObj.config && $rootScope.globalSchObj.config.isFaceOutStop) {
                    $scope.callFaceCheckUp_web(callBack,playVideoFlag);
                }else{
                    $timeout(function(){
                        $timeout(function(){
                            $scope.callFaceCheckUp_web(callBack,playVideoFlag);
                        },500);
                        try{
                            $scope.initCamare();
                        }catch (e){}

                    },30);
                }

            }else{
                $scope.callFaceCheckUp_phone(callBack,playVideoFlag);
            }
        }

        $scope.callFaceCheckUp_phone = function(callBack,playVideoFlag){
            $scope.faceCheckObj.checking = true;
            $("#face_check_container").show();
            $scope.faceCheckObj.faceState = 'notpass';
            $scope.faceCheckObj.successCall = callBack;
            $scope.faceCheckObj.playVideoFlag = playVideoFlag;
            //调起人脸识别之后将视频暂停
            $scope.pauseVideoForFace();
        }

        $scope.checkFaceSubmit = function(){
		    //debugger
		    //隐藏错误消息
            $scope.togleFaceErr();
            //判断
            var photoStr = $("#face_photo").val();
            if(!photoStr || photoStr.length==0){
                if($scope.checkFaceSubmit)$timeout($scope.checkFaceSubmit,1000);
                return;
            }


            var url = "/stu/subLeaFaceMat.jsn";
            //提交人脸到后台
            var param = $("#face_photo_form").serialize();
            $scope.faceCheckObj.faceState = 'wait';
            $rootScope.request(url,param,
                function(data){//成功回调
                    var facetoken = data.data;
                    $scope.faceCheckObj.facetoken = facetoken;
                    $timeout(function(){
                        $scope.checkFaceMatchState(facetoken);
                    },1500);
                },function (data) {
                    $scope.faceCheckObj.faceState = 'notpass';
                    $scope.togleFaceErr('人脸提交失败');
                    //如果是web端 重新调用人脸识别
                    if(isH5Camera()&&!isWx()||isSepcielPhone($scope.phoneTypeFlag)){
                        if($scope.checkFaceSubmit)$timeout($scope.checkFaceSubmit,1000);

                    }
                }
            );


        }
        window.checkFaceSubmit = $scope.checkFaceSubmit;

        //调起摄像机开始拍照
        $scope.takeFacePhoto = function(){
            try{
                if(getOs()=='android'&&isWx()){
                    var jqVideo = $("#id_study_video");
                    var video = jqVideo[0];
                    video.load();
                   /* if($scope.curStuCourse.studentIdCardNum=='130302198902065415'){
                        setTimeout(function(){
                            video.play();
                            video.pause();
                        },100);

                    }*/
                }
            }catch (e){}
            $("#face_photo_camare").click();
        }


        //检查人脸对比的状态
        $scope.checkFaceMatchState = function(faceToken){
            if($scope.faceCheckObj.checkFaceTimes<=0){
                $scope.togleFaceErr('超时请重新拍照');
                //如果是web端 重新调用人脸识别
                if(isH5Camera()&&!isWx()||isSepcielPhone($scope.phoneTypeFlag)){
                    if($scope.checkFaceSubmit)$timeout($scope.checkFaceSubmit,500);
                }

                //然后打开上传按钮
                return;
            }
            $scope.faceCheckObj.checkFaceTimes--;
            var url = "/stu/checkLeaFaceMat.jsn";
            //提交人脸到后台
            var nodeId = '';
            if($scope.curStuCourse.currentNode&&$scope.curStuCourse.currentNode.id!=null){
                nodeId = $scope.curStuCourse.currentNode.id;
            }
            var stuCourseId = $scope.curStuCourse.id;
            var param = "token=" + faceToken +
                "&nodeId=" + nodeId + "&stuCourseId=" + stuCourseId;
            $rootScope.request(url,param,
                function(data){//成功回调
                    var faceState = data.data;
                    if(faceState=='wait'){//如果还在等待3秒继续问询
                        $timeout(function(){
                            $scope.checkFaceMatchState(faceToken);
                        },2000);
                    }else if(faceState=='pass'){
                        //清空失败消息
                        $scope.togleFaceErr('匹配成功');
                        $scope.faceCheckObj.faceState = 'pass';

                    }else {
                        $scope.togleFaceErr('失败请重试');
                        //如果是web端 重新调用人脸识别
                        if(isH5Camera()&&!isWx()||isSepcielPhone($scope.phoneTypeFlag)){
                            if($scope.checkFaceSubmit)$timeout($scope.checkFaceSubmit,500);
                        }
                    }
                }
            );
        }

        //触发显示和隐藏 人脸错误消息
        $scope.togleFaceErr = function(err){
            if(getOs()=='pc'){
                $scope.faceCheckObj.checkFaceTimes = 8;
            }else{
                $scope.faceCheckObj.checkFaceTimes = 20;
            }
            $scope.faceCheckObj.faceState = 'notpass';
            var faceErrInfo = $("#face_error_info");
            var face_error_info_web = $("#face_error_info_web");
            if(err){
                faceErrInfo.html('('+err+')');
                faceErrInfo.show();
                face_error_info_web.html('('+err+')');
                face_error_info_web.show();
            }else{
                faceErrInfo.html('');
                faceErrInfo.hide();
                face_error_info_web.html('');
                face_error_info_web.hide();
            }

        }

        $scope.playVideo = function(){
            var jqVideo = $("#id_study_video");
            var video = jqVideo[0];
            video.play();
            jqVideo.prop("controls","true");
        }
        //手工出发跳转的秒数
        $scope.lastPlaySecondForSeek = 0;
        $scope.lastPlaySecondForSeekStr = "00:00";
        $scope.setSeekHistorySecond = function(sec){
            //
            $scope.lastPlaySecondForSeek = sec;
            $scope.lastPlaySecondForSeekStr = $rootScope.changeSecondToStr(sec);
            //给2分钟思考时间不跳转就消除了
            $timeout( $scope.closeSeekHistory,120000);
        }
        //关闭跳转到历史播放位置
        $scope.closeSeekHistory = function(){
            //
            $scope.lastPlaySecondForSeek = 0;
            $scope.lastPlaySecondForSeekStr = "00:00";
        }
        $scope.seekVideoTimeCount = 30;
        $scope.seekVideo = function(sec,skipPlayState){
            if(!sec){
                return;
            }
            var jqVideo = $("#id_study_video");
            var video = jqVideo[0];
            if(!$scope.videoPlayState && !skipPlayState){
                $rootScope.alertError('请先播放视频');
                // video.play();
                return ;
            }

            $timeout(function() {
                if(video.currentTime<0.1){
                    if($scope.seekVideoTimeCount>0){
                        $scope.seekVideoTimeCount--;
                        $scope.seekVideo(sec,skipPlayState);
                    }else{
                        $scope.seekVideoTimeCount=30;
                    }
                }else{
                    $scope.seekVideoTimeCount=30;
                    $scope.vedioLastTime = sec;
                    video.currentTime = sec;
                    if(!skipPlayState){
                        $scope.freshPlayerSeekState($scope.curStuCourse.currentNode.learnIsFinished=='1');
                        $scope.closeSeekHistory();
                    }
                }
            },500);
        }

        $scope.seekVideoOnce = function(sec){
            if(!sec){
                return;
            }
            var jqVideo = $("#id_study_video");
            var video = jqVideo[0];
            if(!$scope.videoPlayState){
                $rootScope.alertError('请先播放视频');
                // video.play();
                return ;
            }

            $timeout(function() {
                $scope.vedioLastTime = sec;
                video.currentTime = sec;
                $scope.freshPlayerSeekState($scope.curStuCourse.currentNode.learnIsFinished=='1');
                $scope.closeSeekHistory();
            },500);
        }

        $scope.pauseVideoForFace = function(){
            $scope.videoPlayState = false;
            var jqVideo = $("#id_study_video");
            var video = jqVideo[0];
            video.pause();
            $scope.exitFullScreen();
        }

        $scope.pauseVideoOnly = function(){
            $scope.videoPlayState = false;
            var jqVideo = $("#id_study_video");
            var video = jqVideo[0];
            video.pause();
        }

        /**
         * 找到应该学的那个章节
         */
        $scope.findShouldLearnNode = function(courseNode){
            var preLearnNode = courseNode.preLearnNode;
            if(preLearnNode!=null){
                //需要判断上一节课是否学完
                if(preLearnNode.learnIsFinished!='1'){
                    return  $scope.findShouldLearnNode(preLearnNode);
                }else{
                    return courseNode;
                }
            }else{
                return courseNode;
            }
        }

        /**
         * 找到应该答题的那个章节
         */
        $scope.findShouldPractiseNode = function(courseNode){
            var preLearnNode = courseNode.preLearnNode;
            if(preLearnNode!=null){
                //需要判断上一节课是否学完
                if(preLearnNode.questionIsPass!='1'){
                    return  $scope.findShouldPractiseNode(preLearnNode);
                }else{
                    return courseNode;
                }
            }else{
                return courseNode;
            }
        }

        /**
         * 列表点击播放某一个视频
         * @param courseNode
         */
        $scope.clickPlayCourseNode = function(courseNode){
            var preLearnNode = courseNode.preLearnNode;
            if(preLearnNode!=null){
                //需要判断上一节课是否学完
                if(preLearnNode.learnIsFinished!='1'){
                    var temNode = $scope.findShouldLearnNode(preLearnNode);
                    if(temNode!=null){
                        var temPreNode = temNode.preLearnNode;
                        if(temPreNode!=null){//判断前一节点的练习情况
                            if(temPreNode.questionIsPass!='1'){
                                $rootScope.alert('请完成章节练习并达到正确率要求!',function () {
                                    $scope.playCourseNode(temPreNode);
                                    $scope.currTab = 'practise';
                                });
                                return;
                            }
                        }
                        //前一节点为空或者已经完成习题播放当前这个找到的节点
                        $rootScope.alert('请按顺序学习章节内容!',function(){
                            $scope.playCourseNode(temNode);
                        });
                    }


                    return;
                }
                if(preLearnNode.questionIsPass!='1'){
                    $rootScope.alert('请完成章节练习并达到正确率要求!',function () {
                        var temNode = $scope.findShouldPractiseNode(preLearnNode);
                        if(temNode!=null){
                            $scope.playCourseNode(temNode);
                            $scope.currTab = 'practise';
                        }
                    });
                    return;
                }
                //判断上一节课习题是否完成
            }
            $scope.playCourseNode(courseNode);
        }

        /**
         *  课程列表点击播放课程
         * @param courseNode
         */
        $scope.playCourseNode = function(courseNode){
            $scope.closeFaceCheck();
            var jqVideo = $("#id_study_video");
            var video = jqVideo[0];
            if(courseNode.id==$scope.curStuCourse.currentNode.id){
                //判断当前的播放控件是否可播放
                if(!$scope.videoPlayState || video.paused){
                    $scope.playVideo();
                    //判断是否seek如果以前点过再次点击的时候不seek
                    //只有更换课程的时候才会seek
                    if($scope.curStuCourse.seekFlag!='1'){

                        $scope.setSeekHistorySecond($scope.curStuCourse.currentNode.lastLearnLocation);
                        $scope.curStuCourse.seekFlag = '1';
                        $scope.seekVideo($scope.curStuCourse.currentNode.lastLearnLocation,true);
                    }
                }
            }else{
                // $scope.curStuCourse.currentNode = courseNode;
                $scope.freshLearnNode(courseNode,true);
                $scope.playVideo();
                $scope.setSeekHistorySecond($scope.curStuCourse.currentNode.lastLearnLocation);
                $scope.curStuCourse.seekFlag = '1';
                $scope.seekVideo($scope.curStuCourse.currentNode.lastLearnLocation,true);
            }

        }

        $scope.playCourseNodeAfterFace = function(courseNode){
            var succCall = $scope.faceCheckObj.successCall;
            if(succCall!=null){
                //调用成功回调
                succCall();
            }
            debugger;
            if( $scope.faceCheckObj.playVideoFlag!=null && $scope.faceCheckObj.playVideoFlag=='notplay'){
                //不进行播放操作
            }else{
                //如果
                $scope.playCourseNode(courseNode);
                //跳转到上次播放位置
                //这里的这个操作只有安卓手机在人脸识别之后才有用
                $scope.seekVideo($scope.curStuCourse.currentNode.lastLearnLocation,true);
            }

            if(getOs()=='android'&&isWx()){
                try{
                    $scope.destroyCamere();
                }catch(e){}
            }




        }



        /***************** 评论部分代码 begin******************/

        //评论列表分页对象
        $scope.commentListObj = {};
        //input提交评论
        $scope.commentObj = {
            commentText:''
        };
        /**
         * 重新加载到第一页
         */
        $scope.freshCommentOfNode = function(ownerNodeId,forceFlag){
            if($scope.commentObj.ownerNodeId==ownerNodeId && !forceFlag){
                //如果相等那么不在重新加载评论
                return;
            }
            var url = '/comment/listByNode.jsn';
            commonService.generatePageObj($scope.commentListObj,url,10);
            $scope.commentListObj.param = 'queryObj.ownerNodeId=' + ownerNodeId;
            $scope.commentObj.ownerNodeId = ownerNodeId;
            $scope.nextCommentPage();
        }

        $scope.nextCommentPage = function(){
            var promis = commonService.getNextPageObjectByCondition($scope.commentListObj);
            if(promis){
                promis.then(function(list){
                    //设置头像和昵称
                    for(i=0;i<list.length;i++){
                        var comment = list[i];
                        var nickName = comment.stuNickName;
                        var headPhtoto = comment.stuHeadPhoto;
                        var createTime = comment.createTime;
                        if(!nickName || nickName==''){
                            comment.stuNickName = '匿名';
                        }
                        if(!headPhtoto || headPhtoto==''){
                            comment.stuHeadPhoto = "images/user-img.jpg";
                        }else{
                            if(comment.stuHeadPhoto.indexOf("http")!=0){
                                comment.stuHeadPhoto = appConfig.baseImgUrl + comment.stuHeadPhoto ;
                            }
                        }


                    }
                });
            }

        }

        /**
         * 提交评论
         */

        $scope.submitComment = function(){
            var commentText = $scope.commentObj.commentText;
            var ownerNodeId = $scope.commentObj.ownerNodeId;
            if(commentText&&commentText.length>0){
                //提交评论到后台
                var url = "/comment/submitText.jsn";
                //提交人脸到后台
                var param = "ownerNodeId=" + ownerNodeId+ "&commentText="+commentText;
                $scope.commentObj.submitWorking=true;
                $rootScope.request(url,param,
                    function(data){//成功回调
                        $scope.commentObj.submitWorking = false;
                        //清空评论输入框
                        $scope.commentObj.commentText='';
                        //暂时重新加载
                        $scope.freshCommentOfNode(ownerNodeId,true);
                    },
                    function(data){
                        $scope.commentObj.submitWorking = false;
                        $rootScope.alertError(data.errMsg);
                    }
                );
            }

        }

        /***************** 评论部分代码   end******************/

        /***************** 练习部分代码  begin******************/

        $scope.practiseObj = {};
        /**
         * 重新加载到第一页
         */
        $scope.freshPractiseOfNode = function(node,forceFlag){
            var stuNodeId = node.stuNodeId;
            var ownerNodeId = node.id;
            if($scope.practiseObj.ownerNodeId==ownerNodeId && !forceFlag){
                //如果相等那么不在重新加载试题
                return;
            }
            $scope.showPractiseFenxiFlag = false;
            var url = '/practis/listByNodeNew.jsn';
            commonService.generatePageObj($scope.practiseObj,url,8);//一页3道题

            $scope.practiseObj.param =
                'queryObj.ownerNodeId=' + ownerNodeId
                +'&queryObj.stuNodeId=' + stuNodeId;
            $scope.practiseObj.ownerNodeId = ownerNodeId;
            $scope.practiseObj.stuNodeId = stuNodeId;
            $scope.nextPractisePage();
        }

        /**
         * 练习题的下一页
         */
        $scope.nextPractisePage = function(){
            commonService.getNextPageObjectByCondition($scope.practiseObj);
        }

        /**
         * 选择题的答案
         * @param optionObj
         */
        $scope.selectOption = function( que , op){
            if($scope.curStuCourse.currentNode.questionIsFinished=='1'){
                return;
            }
            if(que.questionType=='3'){
                if(op.isSelect!='1'){
                    op.isSelect='1';
                }else{
                    op.isSelect='0';
                }
            }else{
                var opsSize = que.ops.length;
                for(i=0;i<opsSize;i++){
                    var temOp = que.ops[i];
                    temOp.isSelect = "0";
                }
                op.isSelect='1';

            }
        }

        $scope.clickSubmitPractis = function(){
            if($scope.practiseObj.submitWorking)return;
            //判断课程是否学完
            if($scope.curStuCourse.currentNode.learnIsFinished!='1'){
                $rootScope.alertError('请完成视频学习');
                return;
            }
            //判断当前课程是否需要人脸识别
            var needFace = $scope.curStuCourse.courseIsNeedFaceContrast;
            if(needFace!='0'){
                $scope.callFaceCheckUp(function(){
                    $scope.submitPractis();
                },'notplay');
            }else{
                $scope.submitPractis();
            }

        }

        /**
         * 提交练习
         */
        $scope.submitPractis = function(){
            if($scope.practiseObj.submitWorking)return;


            //调起人脸识别
            var ownerNodeId = $scope.practiseObj.ownerNodeId ;
            var stuNodeId = $scope.practiseObj.stuNodeId ;
            //提交练习题答案
            var queList = $scope.practiseObj.list;
            var queIds = '';
            var selectIds = '';
            for(var i=0;i<queList.length;i++){
                var temQue = queList[i];
                var selectOpIds = "@";
                var opsSize = temQue.ops.length;
                for(j=0;j<opsSize;j++){
                    var temOp = temQue.ops[j];
                    if(temOp.isSelect == "1"){
                        if(selectOpIds=="@"){
                            selectOpIds += temOp.id;
                        }else{
                            selectOpIds += "," + temOp.id;
                        }
                    }
                }
                selectOpIds += "@";

                if(!selectOpIds || selectOpIds=='@@'){
                    selectOpId = '@noanswer@';
                }
                if(i==queList.length-1){
                    queIds += temQue.id ;
                    selectIds += selectOpIds;
                }else{
                    queIds += temQue.id + ',';
                    selectIds += selectOpIds + ',';
                }
            }

            if(queIds.length>0){
                $scope.showLoading('练习题提交中...');
                var nextNode = $scope.curStuCourse.currentNode.nextNode;
                var nextNodeId = '';
                if(nextNode!=null){
                    nextNodeId = nextNode.id;
                }
                var url = "/practis/submitPractisNew.jsn";
                var param = 'queIds=' + queIds
                + '&selectIds=' + selectIds
                + '&ownerNodeId=' + ownerNodeId
                + '&stuNodeId=' + stuNodeId
                + '&nextNodeId=' + nextNodeId;


                $scope.practiseObj.submitWorking = true;
                $rootScope.request(url,param,function(data){
                    $scope.hideLoading();
                    $scope.practiseObj.submitWorking = false;
                    var resMap = data.data;
                    var resStuNode = resMap.stuNode;
                    $scope.curStuCourse.currentNode.questionIsFinished = resStuNode.questionIsFinished;
                    $scope.curStuCourse.currentNode.nodeQuestionScore = resStuNode.nodeQuestionScore;
                    $scope.curStuCourse.currentNode.questionIsPass = resStuNode.questionIsPass;
                    var answerStates = resMap.answerStates;
                    var answerStaArr = answerStates.split(",");
                    for(var i=0;i<queList.length;i++){
                        var temQue = queList[i];
                        temQue.answerState = answerStaArr[i];
                    }

                    if($scope.curStuCourse.currentNode.questionIsPass=='1'){
                        $rootScope.alert('本节练习已完成!',function(btn){
                            var nextNode = $scope.curStuCourse.currentNode.nextNode;
                            if(nextNode!=null){
                                $scope.freshLearnNode(nextNode,true);
                                $scope.closeFaceCheck();
                                $scope.doPlayCourseNode(nextNode);
                                if(nextNode.lastLearnLocation>0){
                                    $scope.setSeekHistorySecond(nextNode.lastLearnLocation);
                                }
                                $scope.currTab = 'chapter';
                            }else {
                                $scope.closeFaceCheck();
                                $rootScope.alert('本课程已经学完，可去查看我的证书!');
                            }

                        });

                    }else{
                        $scope.closeFaceCheck();
                        //请他去答题
                        $rootScope.alert('本次练习正确率不合格，请查看解析后重新答题！');

                    }
                },function(data){
                    $scope.hideLoading();
                    $scope.practiseObj.submitWorking = false;
                    $rootScope.alertError(data.errMsg);
                });


            }

        }

        /**
         * 重新答题
         */
        $scope.reTestPractise = function(){
            var ownerNodeId = $scope.practiseObj.ownerNodeId ;
            var stuNodeId = $scope.practiseObj.stuNodeId ;
            $scope.curStuCourse.currentNode.questionIsFinished = "0";
            $scope.curStuCourse.currentNode.nodeQuestionScore = 0;
            var url = "/practis/reTestPractiseNew.jsn";
            var param = 'stuNodeId=' + stuNodeId;
            $scope.practiseObj.submitWorking = true;
            $rootScope.request(url,param,function(data){
                //清空答案
                var queList = $scope.practiseObj.list;
                for(var i=0;i<queList.length;i++){
                    var temQue = queList[i];
                    var selectOpIds = "@";
                    var opsSize = temQue.ops.length;
                    for(j=0;j<opsSize;j++){
                        var temOp = temQue.ops[j];
                        temOp.isSelect = "0"

                    }
                }
                $scope.practiseObj.submitWorking = false;
            },function(data){
                $scope.practiseObj.submitWorking = false;
                $rootScope.alertError(data.errMsg);
            });

        }

        /***************** 练习部分代码   end******************/


        /***************** 非微信端人脸识别部分代码 begin******************/

        $scope.initCamare = function(callback){
            //访问用户媒体设备的兼容方法
            function getUserMedia(constraints, success, error) {
                if (navigator.mediaDevices.getUserMedia) {
                    //最新的标准API
                    navigator.mediaDevices.getUserMedia(constraints).then(success).catch(error);
                } else if (navigator.webkitGetUserMedia) {
                    //webkit核心浏览器
                    navigator.webkitGetUserMedia(constraints,success, error)
                } else if (navigator.mozGetUserMedia) {
                    //firfox浏览器
                    navigator.mozGetUserMedia(constraints, success, error);
                } else if (navigator.getUserMedia) {
                    //旧版API
                    navigator.getUserMedia(constraints, success, error);
                }
            }

            var aVideo=document.getElementById('face_camare_vedio');
            $scope.camareVideo = aVideo;
            var aCanvas=document.getElementById('face_preview_canvas');
            $scope.aCanvas = aCanvas;
            $scope.canvasContext=aCanvas.getContext('2d');


            /*navigator.getUserMedia  = navigator.getUserMedia ||
                navigator.webkitGetUserMedia ||
                navigator.mozGetUserMedia ||
                navigator.msGetUserMedia;//获取媒体对象（这里指摄像头）
            navigator.getUserMedia({video:true}, gotStream, noStream);*///参数1获取用户打开权限；参数二成功打开后调用，并传一个视频流对象，参数三打开失败后调用，传错误信息
            if (navigator.mediaDevices.getUserMedia || navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia) {
                //调用用户媒体设备, 访问摄像头
                getUserMedia({video : true}, gotStream, noStream);
            } else {
                alert('不支持访问用户媒体');
            }
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
                aVideo.play();
                stream.onended = noStream;
                aVideo.onloadedmetadata = function () {
                    function takerealFaceFn(){
                        var base64StrFace = $scope.cutPicker();
                        $("#face_photo").val(base64StrFace.substring("data:image/jpeg;base64,".length));
                        if($rootScope.globalSchObj && $rootScope.globalSchObj.config && $rootScope.globalSchObj.config.isFaceOutStop) {
                            $("#real_face_img").prop("src", base64StrFace);
                            $("#real_face_display").show();
                        }
                    }
                    if(callback){
                        takerealFaceFn();
                        callback();
                    }
                    //将图片展示到右下角 进行人离线断监测
                    $scope.takerealFaceInterval = setInterval(
                        function(){
                            takerealFaceFn();
                        },500
                    );
                    //TODO: 后边将isFaceOutStop这个参数设置学校配置中去
                    if($rootScope.globalSchObj && $rootScope.globalSchObj.config && $rootScope.globalSchObj.config.isFaceOutStop) {
                        //电脑端的人离线断判断
                        $scope.checkFaceOutInterval = setInterval(
                            function(){
                              if( $scope.checkFaceOutSubmit){
                                  $scope.checkFaceOutSubmit();
                              }
                            },30000 //30秒整一次
                        );
                    }
                };
            }
            function noStream(err) {
                alert(err);
            }
        }




        $scope.cutPicker= function(){
            var canv = $("#face_preview_canvas");
            var width = canv.attr('width');
            var height = canv.attr('height');
            $scope.canvasContext.drawImage($scope.camareVideo, 0, 0, width, height);
            var base64StrFace = $scope.aCanvas.toDataURL("image/jpeg");
            return base64StrFace;
        }

        /**
         *  调起web端人脸识别
         */
        $scope.callFaceCheckUp_web = function(callBack,playVideoFlag){
            $scope.faceCheckObj.faceState = 'notpass';
            $scope.faceCheckObj.successCall = callBack;
            $scope.faceCheckObj.playVideoFlag = playVideoFlag;
            $("#face_check_container_web").show();
            $scope.pauseVideoForFace();
            $scope.checkFaceSubmit();

            //发起人脸识别的提交
            /*var photoStr = $("#face_photo").val();
            if(photoStr && photoStr.length>0){
                $scope.checkFaceSubmit();
            }else{
                $timeout(function(){
                    $scope.callFaceCheckUp_web(callBack);
                } ,1000);
            }*/



        }
        if($rootScope.globalSchObj && $rootScope.globalSchObj.config && $rootScope.globalSchObj.config.isFaceOutStop) {
             if(isH5Camera()){//如果是pc那么启动摄像头
                 $timeout(function(){
                     $scope.initCamare();
                 },300);
             }
        }

        //人脸检测相关对象
        $scope.faceOutObj = {
            faceOutDetectCount:0
        };
        /**
         * 人离线断的判断
         */
        $scope.checkFaceOutSubmit = function(){
            //如果视频暂停中就不在检测
            $scope.videoPlayState
            if(!$scope.videoPlayState){
                $scope.faceOutObj.faceOutDetectCount=0;
                return;
            }

            //提交图片到后台 进行人脸统计
            var url = "/stu/subFaceDetect.jsn";
            //提交人脸到后台
            var param = $("#face_photo_form").serialize();
            $rootScope.request(url,param,
                function(data){//成功回调
                    var facetoken = data.data;
                    //询问本次人脸个数统计
                    $timeout(function(){
                        $scope.checkFaceDetectState(facetoken);
                    },5000);
                },function (data) {
                    $rootScope.alertError(data.errMsg);
                }
            );
        }

        //然后计数超过两次没有人脸就停止学习需要人脸匹配
        $scope.checkFaceDetectState = function(facetoken,notnext){
            //提交图片到后台 进行人脸统计
            var url = "/stu/checkFaceDetect.jsn";
            //提交人脸到后台
            var param = "token=" + facetoken;
            $rootScope.request(url,param,
                function(data){//成功回调
                    var faceNum = data.data;
                    if(faceNum>0 ||faceNum==-99 ){

                        if(!notnext){
                            $timeout(function(){
                                //传参下次不在重复请求了
                                $scope.checkFaceDetectState(facetoken,true);
                            },10000);

                        }else{
                            $scope.faceOutObj.faceOutDetectCount=0;
                        }

                    }else {
                        $scope.faceOutObj.faceOutDetectCount++;
                        if($scope.faceOutObj.faceOutDetectCount<2){
                            //再次发起一次人脸检测
                            $timeout($scope.checkFaceOutSubmit,1000);
                        }else{
                            //将视频暂停
                            $scope.pauseVideoOnly();
                            //或者调起人脸识别
                            // $scope.callFaceCheckUp();
                        }

                    }
                },function (data) {
                    $rootScope.alertError(data.errMsg);
                }
            );
        }

        /***************** 非微信端人脸识别部分代码   end******************/
        $scope.destroyCamere = function(){
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
        }

        //scope销毁时的监听
        $scope.$on('$destroy', function() {
            try{
                if ($scope.disableSeekInterval) {
                    clearInterval($scope.disableSeekInterval);
                }
            }catch(e){}
            try{
                if ($scope.timeCountInterval) {
                    clearInterval($scope.timeCountInterval);
                }
            }catch(e){}
            try{
                if ($scope.takerealFaceInterval) {
                    clearInterval($scope.takerealFaceInterval);
                }
            }catch(e){}
            try{
                if($scope.checkFaceOutInterval){
                    clearInterval($scope.checkFaceOutInterval);
                }
            }catch(e){}
            try{
                /*if($scope.timeCallStudentName){
                    clearInterval($scope.timeCallStudentName);
                }*/
            }catch(e){}

            //如果有进行的人脸检查也需要关掉
            $scope.checkFaceSubmit = false;//递归的调用
            try{
                $scope.destroyCamere();
            }catch(e){}

        });

        //开启监控切换课程事件
        $scope.$watch("curStuCourse.currentNode.id",function(newValue,oldValue){
            if(newValue){
                try{
                    $scope.freshPractiseOfNode($scope.curStuCourse.currentNode);
                }catch (e){ }
                try{
                    $scope.freshCommentOfNode(newValue);
                }catch (e){ }
            }
        });

        //显示loading
        $scope.showLoading = function(msg){
            $("#loading_msg_container").show();
            $("#loading_msg").html(msg);
        }
        //隐藏loading
        $scope.hideLoading = function(){
            $("#loading_msg_container").hide();
            $("#loading_msg").html('');
        }
	}]);
});