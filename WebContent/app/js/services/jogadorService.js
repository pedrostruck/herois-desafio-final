moduleApp.factory("jogadorService", function ($http) {

	const cadstrarUrl = 'http://localhost:8080/curso-hackaton-cdi/jogador/cadastrar';
    const loginUrl = 'http://localhost:8080/curso-hackaton-cdi/jogador/login';
    
    var _jogador = {
        nickname: null,
        senha: null,
        personagem: {}
    };

    var _dadosLogin = {
        nickname: null,
        senha: null
    };

	function _inserirJogador(jogador) {
        return $http.post(cadstrarUrl, jogador);
    };

    function _login(dadosLogin) {
        return $http.post(loginUrl, dadosLogin);
    };

	return {
        inserirJogador: _inserirJogador,
        login: _login,
        jogador: _jogador,
        dadosLogin: _dadosLogin
    };
});