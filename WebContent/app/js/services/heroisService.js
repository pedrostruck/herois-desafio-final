moduleApp.factory('heroisService', function($http) {
	const listarHeroisUrl = 'http://localhost:8080/curso-hackaton-cdi/heroi/listAll';

	const _getHerois = function() {
		return $http.get(listarHeroisUrl);
	};

	return {
		getHerois: _getHerois
	};
});
