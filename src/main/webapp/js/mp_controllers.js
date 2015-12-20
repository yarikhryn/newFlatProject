/**
 * Created by busik on 12.12.15.
 */

angular.module('DuckerApp', ['ngCookies', 'ngRoute'])
    .config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
        //$locationProvider.html5Mode({
        //    enabled: true,
        //    requireBase: false
        //});
        //$locationProvider.hashPrefix('!');
        $routeProvider.
        when('/user/:userId', {
            templateUrl: '/user_template.html',
            controller: 'UserController'
        }).
        when('/', {
            templateUrl: '/main_page_template.html',
            controller: 'UserTasksList'
        }).
        when('/task/new', {
            templateUrl: '/new_task_page_template.html',
            controller: 'NewTaskPageController'
        }).
        when('/task/:task_id', {
            templateUrl: '/task_page_template.html',
            controller: 'TaskPageController'
        }).
        otherwise({
            redirectTo: '/error'
        });
    }])
    .controller('UserTasksList', ['$scope', '$http', '$cookies', '$timeout',
        function ($scope, $http, $cookies, $timeout) {

            $scope.user = null;

            $scope.user_tasks = [];

            $scope.activity_stream = [];

            var count = 0;
            var CALL_TIMES = 5;

            $scope.load_user_tasks = function () {
                console.log("Try to load user tasks data");
                $http.get("api/tasks/search/?user_id=" + $cookies.get("user_id")).
                success(
                    function (data) {
                        $scope.user_tasks = data;
                        console.log(data);
                    }).
                error(
                    function () {
                        $timeout(function () {
                            if (count < CALL_TIMES) {
                                count++;
                                $scope.load_user_tasks();
                            } else {
                                alert("Failed to load User tasks");
                                count = 0;
                            }
                        }, (500 + Math.floor(Math.random() * 10)) * count);
                    });
            }

            $scope.load_current_user = function () {
                console.log("Try to load current user");
                $http.get('/api/users/' + $cookies.get("user_id")).
                success(
                    function(data){
                        $scope.user = data;
                        console.log(data);
                    }).
                error(
                    function () {
                        $timeout(function () {
                            if (count < CALL_TIMES) {
                                count++;
                                $scope.load_current_user();
                            } else {
                                alert("Failed to load User data");
                                count = 0;
                            }
                        }, (500 + Math.floor(Math.random() * 10)) * count);
                    });
            }
            $scope.load_activity_stream = function () {
                console.log("Try to load Activity stream");
                $http.get("api/tasks/all").
                success(
                    function (data) {
                        $scope.activity_stream = data;
                        console.log(data);
                    }).
                error(
                    function () {
                        $timeout(function () {

                            if (count < CALL_TIMES) {
                                count++;
                                $scope.load_activity_stream();
                            } else {
                                alert("Failed to load Activity stream data");
                                count = 0;
                            }
                        }, (500 + Math.floor(Math.random() * 10)) * count);
                    });
            }

            $http.get('/api/users/' + $cookies.get("user_id")).
            success(
                function (data) {
                    $scope.user = data;
                    console.log(data);
                }).
            error(
                function () {

                });

            $scope.load_activity_stream();
            $scope.load_current_user();
            $scope.load_user_tasks();
        }
    ])
    .controller('UserController', ['$scope', '$http', '$cookies', '$routeParams', '$timeout',
        function ($scope, $http, $cookies, $routeParams, $timeout) {
            console.log("USER CONTROLLER");
            $scope.selected_user = null;
            var count = 0;
            var CALL_TIMES = 5;

            $scope.get_user = function () {
                console.log("Try to load user data");
                $http.get('/api/users/' + $cookies.get("user_id")).
                success(
                    function (data) {
                        $scope.user = data;
                        console.log(data);
                    }).
                error(
                    function () {
                        $timeout(function () {
                            if (count < CALL_TIMES) {
                                count++;
                                $scope.get_user();
                            } else {
                                alert("Failed to load User data");
                                count = 0;
                            }
                        }, (500 + Math.floor(Math.random() * 10)) * count);
                    });

                $http.get('/api/users/' + $routeParams.userId).
                success(
                    function (data, status, headers, config) {
                        $scope.selected_user = data;
                        if (typeof($scope.selected_user.photo ) == "undefined") {
                            $scope.selected_user.photo = 'resources/img/No_image.jpg'
                        }
                        console.log(data);
                    }).
                error(
                    function () {
                        $timeout(function () {
                            if (count < CALL_TIMES) {
                                count++;
                                $scope.get_user();
                            } else {
                                alert("Failed to load User data");
                                count = 0;
                            }
                        }, (500 + Math.floor(Math.random() * 10)) * count);
                    });
            }

            $http.get('/api/users/' + $cookies.get("user_id")).
            success(
                function (data) {
                    $scope.user = data;
                    console.log(data);
                }).
            error(
                function () {

                });

            $scope.get_user();
        }])
    .controller('TaskPageController', ['$scope', '$http', '$cookies', '$routeParams', function ($scope, $http, $cookies, $routeParams) {
        console.log("TaskPageController");
        $scope.selected_user = null;

        $scope.selected_task = null;

        $http.get('/api/users/' + $cookies.get("user_id")).
        success(
            function (data) {
                $scope.user = data;
                console.log(data);
            }).
        error(
            function () {

            });

        $http.get('api/tasks/' + $routeParams.task_id).
        success(
            function (data) {
                $scope.selected_task = data;
                console.log(data);
            }).
        error(
            function () {

            });

        $scope.comment = {text: null};

        $scope.doComment = function () {
            console.log($scope.comment);
            if (!$scope.comment.text) {
                alert("Comment is empty")
            } else {
                $http.post("/api/tasks/" + $scope.selected_task.id + "/add_comment", JSON.stringify($scope.comment))
                    .success(function (data) {
                        $scope.selected_task.comments.push(data);
                    })
                    .error(function () {
                        alert("Smth error")
                    })
            }
        }

        $scope.updateStatus = function (nextStatus) {
            $http.post("api/tasks/" + $scope.selected_task.id + "/update_status", JSON.stringify({
                    id: -1,
                    name: nextStatus
                }))
                .success(function (data) {
                    $scope.selected_task = data
                })
        }
    }])
    .controller('NewTaskPageController', ['$scope', '$http', '$cookies', '$location', function ($scope, $http, $cookies, $location) {
        console.log("NewTaskPageController");
        $scope.user = null;

        $http.get('/api/users/' + $cookies.get("user_id")).
        success(
            function (data) {
                $scope.user = data;
                console.log(data);
            }).
        error(
            function () {

            });

        $scope.task = {
            name: null,
            description: null,
            assigner: null
        };

        $scope.create_task = function () {
            var flag = true;
            console.log($scope.task);
            if (!$scope.task.name) flag = false;
            if (!$scope.task.description) flag = false;
            if (!$scope.task.assigner) flag = false;

            if (flag) {
                $http.post("/api/tasks/create", JSON.stringify($scope.task)).
                success(
                    function (TaskId) {
                        $location.path('/task/' + TaskId);
                    }).
                error(
                    function () {
                        alert('server error');
                    });
            } else {
                alert("fill fields");
            }

        }
    }]);
