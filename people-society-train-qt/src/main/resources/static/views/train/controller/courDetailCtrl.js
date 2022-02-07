/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('courDetailCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
        //默认显示tab页面
        $scope.currTab = 'courseDesc';
        //URL参数中的当前学习的课程
        $scope.courseId = $routeParams.courseId;
        //当前课程
        $scope.curCourse = {}
        //课程小节信息
        $scope.courseNodes = [];
        //课程评论信息
        $scope.courseComments = [];

        $scope.currentNav = "";

        //查询课程详情
        $scope.courseDetail = function(){
            var url = '/cour/loadCourseInfoById.jsn';
            var param = "courseId="+$scope.courseId;
            $scope.request(url,param,function(data){
                if(data.success){
                    //课程详情
                    $scope.curCourse = data.data.schoolCourse;
                    //设置图片
                    var courseImage = $scope.curCourse.courseImage;
                    if(courseImage && courseImage.indexOf('http')!=0){
                        courseImage = appConfig.baseImgUrl + courseImage;
                        $scope.curCourse.courseImage = courseImage;
                    }
                    //课程小结
                    $scope.courseNodes = data.data.courseNodes;
                    //设置各个视频的学习进度
                    angular.forEach($scope.courseNodes,
                        function(courseNode,index,array){
                            //计算时长显示
                            var nodeTimeLength = courseNode.nodeTimeLength;
                            if(nodeTimeLength!=null){
                                //将秒数转换成分:秒
                                courseNode.nodeTimeLengthStr = $rootScope.changeSecondToStr(nodeTimeLength);
                            }
                        }
                    );
                    //处理小节的时长
                    //课程评论
                    $scope.courseComments = data.data.courseComs;
                    for(var i=0;i<$scope.courseComments.length;i++){
                        var nickName = $scope.courseComments[i].nickName;
                        if(!nickName || nickName==''){
                            $scope.courseComments[i].stuNickName = '匿名';
                        }
                        var headPhoto = $scope.courseComments[i].headPhoto;
                        if(!headPhoto || headPhoto==''){
                            $scope.courseComments[i].stuHeadPhoto = "images/user-img.jpg";
                        }
                    }
                }else{
                    $rootScope.errHttpAction(data);
                }
            });
        }

        //tab切换事件
        $scope.changeTab = function(tabName){
            $scope.currTab = tabName;
        }

        //立即学习
        $scope.gotoStudy = function(){
            var url = "/stuCourse/goToStudyCourseById.jsn";
            var param = "courseId="+$scope.courseId;
            $rootScope.request(url,param,function(data){
                if(data.success){
                    if(data.data == null){
                        $rootScope.alert("请联系学校管理员添加学习课程！");
                    }else{
                        var stuCourseId = data.data.id;
                        if(getOs()=='pc'){
                            $rootScope.changePage("/sdudyweb/"+stuCourseId);
                        }else{
                            $rootScope.changePage("/sdudy/"+stuCourseId);
                        }
                    }
                }else{
                    $rootScope.errHttpAction(data);
                }
            })

        }

        //查询课程详情
        $scope.courseDetail();

	}]);
});