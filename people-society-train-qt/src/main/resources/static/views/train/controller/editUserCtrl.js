/**
 *  首页
 */
define(['app','commonService'], function(app){
	return app.controller('editUserCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
		$scope.curStudent = {};
		$scope.isRealName = "否";
		$scope.classList = [];

        /*获取学员信息*/
        $scope.getUserInfo = function(){
            var url = "/stu/getUserInfo.jsn";
            $rootScope.request(url,{},function(data){
                if(data.success){
                    $scope.curStudent = data.data.student;
                    $scope.curStudent.className = data.data.className;
                    var isRealNameAuth = $scope.curStudent.isRealNameAuth;
                    if(isRealNameAuth == '1'){
                        $scope.curStudent.isRealNameAuth = true;
                        $scope.isRealName = "是";
                    }else{
                        $scope.curStudent.isRealNameAuth = false;
                        $scope.isRealName = "否";
                    }
                    //身份证号加密
                    var studentIdCardNum = $scope.curStudent.studentIdCardNum;
                    $scope.curStudent.studentIdCardNum = studentIdCardNum.substring(0,6)+"******"+studentIdCardNum.substring(14,18);
                    //设置图片
                    var headPhoto = $scope.curStudent.headPhoto;
                    if(headPhoto&& headPhoto.length>0&&headPhoto.indexOf("http")!=0){
                        headPhoto =  appConfig.baseImgUrl + headPhoto;
                        $scope.curStudent.headPhoto = headPhoto;
                    }else{
                        headPhoto = "/images/user-img.jpg"
                        $scope.curStudent.headPhoto = headPhoto;
                    }
                    /*var className = $scope.curStudent.className;
                    if(typeof (className) == 'undefined'){
                        $scope.selectAllClass();
                    }*/
                    //昵称
                    /*var nickName = $scope.curStudent.nickName;
                    if(nickName == null){
                        nickName = "请设置昵称";
                        $scope.curStudent.nickName = nickName;
                    }*/
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //更新学员的头像和昵称
        $scope.updateStuInfo = function(){

            var url = "/stu/updateHeadPhotoAndClass.jsn";
            var param = $("#studentForm").serialize();
            $rootScope.request(url,param,function(data){
                if(data.success){
                    $rootScope.alert("保存成功！",function(){
                        $rootScope.changePage("/userInfo");
                    });
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //查询所有的班级信息
        $scope.selectAllClass = function(){
            var url = "/school/selectAllClassBySchool.jsn";
            $rootScope.request(url,"",function(data){
                if(data.success){
                    $scope.classList = data.data;
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        //头像上传触发事件
        $scope.updateLoadFacePhoto = function(){
            $("#uploadHeadPhoto").click();
        }

        //去到学员信息页面
        $scope.gotoUserInfoPage = function(){
            $rootScope.changePage("/userInfo");
        }

        //获取学员信息
        $scope.getUserInfo();

	}]);
});