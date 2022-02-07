/**
 *  首页
 */
define(['app','commonService','ngInfiniteScroll'], function(app){
	return app.controller('examCtrl',
        ['$scope','$rootScope','$http','$timeout','appConfig','commonService','$routeParams','$interval',
	function ($scope,$rootScope,$http,$timeout,appConfig,commonService,$routeParams,$interval) {
        $scope.paperId = $routeParams.paperId;
        $scope.minute = 0;
        $scope.second = "00";

        $scope.pageInfoObj = {};
        $scope.ansQuesCount = 0;

        //查询学员试卷
        $scope.searchStuQuestionListPage = function(){
            var url = "/test/selectStudentTestPaperQuestion.jsn";
            var param = "queryObj.ownerTestPaperId="+$scope.paperId;
            commonService.generatePageObj($scope.pageInfoObj,url,3);
            $scope.pageInfoObj.param = param;
            $scope.nextQuestionPage();
        }

        //下一页
        $scope.nextQuestionPage = function(){
            commonService.getNextPageObjectByCondition($scope.pageInfoObj);
        }

        //查询试卷剩余时长
        $scope.selectPaperTimeLength = function(){
            var url = "/test/selectPaperTimeLength.jsn";
            var param = "studentPaperId="+$scope.paperId;
            $rootScope.request(url,param,function(data){
                if(data.success){
                    var testLength = data.data;
                    if(testLength <= 0){
                        $rootScope.alert("考试已经结束！",function(btn){
                            $rootScope.changePage("/examResult/"+$scope.paperId);
                        })
                    }else{
                        $scope.minute = parseInt(data.data);
                        //考试开始倒计时
                        intervalTestLength();
                    }
                }else{
                    $rootScope.errHttpAction(data);
                }
            })
        }

        function intervalTestLength(){
            //倒计时
            $interval(function(){
                var minute = parseInt($scope.minute);
                var second  = parseInt($scope.second);
                //如果时间到了交卷
                if(minute <= 0 && second <= 0){
                    $scope.autoSubmitPaper();
                }
                if(second == 0){
                    minute--;
                    $scope.minute = minute;
                    $scope.second = 59;
                }else{
                    second--;
                    if(second < 10){
                        $scope.second = "0"+second;
                    }else{
                        $scope.second = second;
                    }
                }
            },1000);
        }

        //统计一下答题的数量
        $scope.computeAnsQuesCount = function(que,op,qIndex){
            var opsSize = que.questionOptionList.length;
            if(que.testPaperQuestionType=='3'){
                if(op.isSelect!='1'){
                    op.isSelect='1';
                }else{
                    op.isSelect='0';
                }
                var selectOpId = "";
                for(var i=0;i<opsSize;i++){
                    var queOp = que.questionOptionList[i];
                    if(queOp.isSelect == '1'){
                        if(selectOpId == ''){
                            selectOpId = que.questionOptionList[i].id;
                        }else{
                            selectOpId += "@" + que.questionOptionList[i].id;
                        }
                    }
                }
                $scope.pageInfoObj.list[qIndex].selectOpId = selectOpId;
            }else{
                for(var i=0;i<opsSize;i++){
                    var temOp = que.questionOptionList[i];
                    temOp.isSelect = "0";
                }
                op.isSelect='1';
                $scope.pageInfoObj.list[qIndex].selectOpId = op.id;
            }

            var list = $scope.pageInfoObj.list;
            var ansQuesCount = 0;
            for(var i=0;i<list.length;i++){
                if(typeof(list[i].selectOpId) != 'undefined'){
                    ansQuesCount++;
                }
            }
            $scope.ansQuesCount = ansQuesCount;
        }
        /*$scope.computeAnsQuesCount = function(index,opId){
            $scope.pageInfoObj.list[index].selectOpId = opId;
            var list = $scope.pageInfoObj.list;
            var ansQuesCount = 0;
            for(var i=0;i<list.length;i++){
                if(typeof(list[i].selectOpId) != 'undefined'){
                    ansQuesCount++;
                }
            }
            $scope.ansQuesCount = ansQuesCount;
        }*/

        //学员提交试卷
        $scope.submitPaper = function(){
            $rootScope.alertConfirm("确定要提交试卷吗？",function(btn){
                if(btn){
                    var quesList = $scope.pageInfoObj.list;
                    var quesIds = "";
                    var selectOpIds = "";
                    for(var i=0;i<quesList.length;i++) {
                        var ques = quesList[i];
                        var selectOpId = ques.selectOpId;
                        if (!selectOpId || selectOpId == '') {
                            selectOpId = 'noanswer';
                        }
                        if (i == quesList.length - 1) {
                            quesIds += ques.id;
                            selectOpIds += ques.selectOpId;
                        } else {
                            quesIds += ques.id + ',';
                            selectOpIds += ques.selectOpId + ',';
                        }
                    }
                    if(quesIds.length > 0){
                        var url = "/test/submitPaper.jsn";
                        var param = "quesIds="+quesIds+"&selectOpIds="+selectOpIds+"&testPaperId="+$scope.paperId;
                        $rootScope.request(url,param,function(data){
                            if(data.success){
                                $rootScope.changePage("/examResult/"+$scope.paperId);
                            }else{
                                $rootScope.errHttpAction(data);
                            }
                        })
                    }
                }

            })
        }

        //自动提交试卷
        $scope.autoSubmitPaper = function(){
            var quesList = $scope.pageInfoObj.list;
            var quesIds = "";
            var selectOpIds = "";
            for(var i=0;i<quesList.length;i++) {
                var ques = quesList[i];
                var selectOpId = ques.selectOpId;
                if (!selectOpId || selectOpId == '') {
                    selectOpId = 'noanswer';
                }
                if (i == quesList.length - 1) {
                    quesIds += ques.id;
                    selectOpIds += ques.selectOpId;
                } else {
                    quesIds += ques.id + ',';
                    selectOpIds += ques.selectOpId + ',';
                }
            }
            if(quesIds.length > 0){
                var url = "/test/submitPaper.jsn";
                var param = "quesIds="+quesIds+"&selectOpIds="+selectOpIds+"&testPaperId="+$scope.paperId;
                $rootScope.request(url,param,function(data){
                    if(data.success){
                        $rootScope.changePage("/examResult/"+$scope.paperId);
                    }else{
                        $rootScope.errHttpAction(data);
                    }
                })
            }
        }

        //查询题目列表
        $scope.searchStuQuestionListPage();
        //查询考试时长
        $scope.selectPaperTimeLength();

	}]);
});