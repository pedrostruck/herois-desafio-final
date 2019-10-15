angular.module("heroi").factory("heroisService", function ($http) {
	var baseUrl = 'http://localhost:8080/curso-hackaton-cdi/heroi';
	var _getHerois = function () {
		return $http.get(baseUrl);
	};

	return {
		getHerois: _getHerois
	};	
});