//package br.com.leonardo.bibliotecaspring.entity;
//
//import br.com.leonardo.bibliotecaspring.enums.Acesso;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
//@Entity
//@Table(name = "usuario")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@EqualsAndHashCode(of = "id")
//public class Usuario implements UserDetails {
//
//   public Usuario (String login, String senha, Acesso acesso){
//        this.login = login;
//        this.senha = senha;
//        this.acesso = acesso;
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private String id;
//
//    private String login;
//
//    private String senha;
//
//    @Enumerated(EnumType.STRING)
//    private Acesso acesso;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if(this.acesso == Acesso.ADMIN){
//            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
//        }
//        else{
//            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
//        }
//    }
//
//    @Override
//    public String getPassword() {
//        return senha;
//    }
//
//    @Override
//    public String getUsername() {
//        return login;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
