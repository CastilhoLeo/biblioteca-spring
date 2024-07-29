//package br.com.leonardo.bibliotecaspring.controller;
//
//import br.com.leonardo.bibliotecaspring.dto.AutenticacaoDTO;
//import br.com.leonardo.bibliotecaspring.dto.LoginResponseDTO;
//import br.com.leonardo.bibliotecaspring.dto.RegistroDTO;
//import br.com.leonardo.bibliotecaspring.entity.Usuario;
//import br.com.leonardo.bibliotecaspring.infra.config.TokenService;
//import br.com.leonardo.bibliotecaspring.repository.UsuarioRepository;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/auth")
//public class UsuarioController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private TokenService tokenService;
//
//
//
//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO dados){
//        var loginSenha = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
//        var auth = this.authenticationManager.authenticate(loginSenha);
//
//        var token = tokenService.generateToken((Usuario)auth.getPrincipal());
//        return ResponseEntity.ok(new LoginResponseDTO(token));
//    }
//
//    @PostMapping("/registro")
//    public ResponseEntity registro(@RequestBody @Valid RegistroDTO dados){
//        if(this.usuarioRepository.findByLogin(dados.login()) != null)
//            return ResponseEntity.badRequest().build();
//       String encryptoPassword = new BCryptPasswordEncoder().encode(dados.senha());
//       Usuario novoUsuario = new Usuario(dados.login(), encryptoPassword, dados.acesso());
//
//       this.usuarioRepository.save(novoUsuario);
//
//       return ResponseEntity.ok().build();
//    }
//
//}
