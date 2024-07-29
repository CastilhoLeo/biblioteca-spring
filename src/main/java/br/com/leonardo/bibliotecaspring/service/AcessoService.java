//package br.com.leonardo.bibliotecaspring.service;
//
//import br.com.leonardo.bibliotecaspring.repository.UsuarioRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AcessoService implements UserDetailsService {
//
//    @Autowired
//    private UsuarioRepository acessoRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return acessoRepository.findByLogin(username);
//    }
//}
