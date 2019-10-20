moduleApp.factory('batalhaService', function($http) {
	const getOponentesUrl = 'http://localhost:8080/curso-hackaton-cdi/batalha/getOponentes';

	const _getOponentes = function(jogadorLogado) {
		return $http.post(getOponentesUrl, jogadorLogado);
	};

	const _batalhar = function(nickJogador, nickOponente) {
		let batalharUrl = `http://localhost:8080/curso-hackaton-cdi/batalha/batalhar/${nickJogador}/${nickOponente}`;
		return $http.post(batalharUrl);
	};

	return {
		getOponentes: _getOponentes,
		batalhar: _batalhar
	};
});
