moduleApp.factory("batalhaService", function ($http) {

	const baseUrl = 'http://localhost:8080/curso-hackaton-cdi/batalha';

	const getOponentesUrl = 'http://localhost:8080/curso-hackaton-cdi/batalha/getOponentes';

	const _batalharVsJogador = function (jogador,jogador2) {
		return $http.post(baseUrl,jogador,jogador2);
	};

	const _getJogadoresOponentes = function() {
		return $http.get(getOponentesUrl);
	};

	return {
		batalharVsJogador: _batalharVsJogador,
		getJogadoresOponentes: _getJogadoresOponentes
	};

});