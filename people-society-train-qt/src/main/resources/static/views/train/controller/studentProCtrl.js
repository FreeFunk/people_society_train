/**
 *  首页
 */
define(['app','commonService'], function(app){
    return app.controller('studentProCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams',
            function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams) {
                $scope.curStudent = {};
                $scope.stuAndCourseId = $routeParams.stuAndCourseId;
                /*获取学员信息*/
                $scope.getUserInfo = function(){
                    var url = "/stuCourse/studentProInfo.jsn";
                    var param = "stuAndCouId="+$scope.stuAndCourseId
                    $rootScope.request(url,param,function(data){
                        if(data.success){
                            $scope.curStudent = data.data;
                        }else{
                            $rootScope.errHttpAction(data);
                        }
                    })
                }

                $scope.getUserInfo();

            }]);
});