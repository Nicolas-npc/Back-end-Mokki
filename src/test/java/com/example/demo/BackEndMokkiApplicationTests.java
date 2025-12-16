package com.example.demo;

import com.example.demo.Model.ProductoModel;
import com.example.demo.Model.UserModel;
import com.example.demo.Repository.ProductoRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.ProductoService;
import com.example.demo.Service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceUnitTests {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProductoService productoService;

    @InjectMocks
    private UserService userService;

    // ========== Tests de ProductoService ==========

    @Test
    @DisplayName("ProductoService - obtenerTodos devuelve lista de productos")
    void productoService_obtenerTodos_devuelveListaProductos() {
        ProductoModel producto = new ProductoModel();
        producto.setId(1);
        producto.setNombre("Café Espresso");
        producto.setPrecio(2500.0);
        
        when(productoRepository.findAll()).thenReturn(List.of(producto));

        List<ProductoModel> productos = productoService.obtenerTodos();

        assertThat(productos).hasSize(1);
        assertThat(productos.get(0).getNombre()).isEqualTo("Café Espresso");
    }

    @Test
    @DisplayName("ProductoService - actualizarProducto lanza error si no existe")
    void productoService_actualizarProducto_lanzaErrorSiNoExiste() {
        when(productoRepository.findById(1)).thenReturn(Optional.empty());
        ProductoModel nuevoProducto = new ProductoModel();

        assertThatThrownBy(() -> productoService.actualizarProducto(1, nuevoProducto))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Producto no encontrado");
    }

    @Test
    @DisplayName("ProductoService - crearProducto guarda producto correctamente")
    void productoService_crearProducto_guardaProductoCorrectamente() {
        ProductoModel producto = new ProductoModel();
        producto.setNombre("Café Latte");
        producto.setPrecio(3000.0);
        
        ProductoModel productoGuardado = new ProductoModel();
        productoGuardado.setId(1);
        productoGuardado.setNombre("Café Latte");
        productoGuardado.setPrecio(3000.0);
        
        when(productoRepository.save(producto)).thenReturn(productoGuardado);

        ProductoModel resultado = productoService.crearProducto(producto);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1);
        assertThat(resultado.getNombre()).isEqualTo("Café Latte");
    }

    @Test
    @DisplayName("ProductoService - buscarPorId retorna producto cuando existe")
    void productoService_buscarPorId_retornaProductoCuandoExiste() {
        ProductoModel producto = new ProductoModel();
        producto.setId(1);
        producto.setNombre("Café Espresso");
        
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        ProductoModel resultado = productoService.buscarPorId(1);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1);
        assertThat(resultado.getNombre()).isEqualTo("Café Espresso");
    }

    @Test
    @DisplayName("ProductoService - buscarPorId retorna null cuando no existe")
    void productoService_buscarPorId_retornaNullCuandoNoExiste() {
        when(productoRepository.findById(999)).thenReturn(Optional.empty());

        ProductoModel resultado = productoService.buscarPorId(999);

        assertThat(resultado).isNull();
    }

    // ========== Tests de UserService ==========

    @Test
    @DisplayName("UserService - obtenerTodos devuelve lista de usuarios")
    void userService_obtenerTodos_devuelveListaUsuarios() {
        UserModel usuario = new UserModel();
        usuario.setId(1);
        usuario.setNombre("Juan Pérez");
        usuario.setEmail("juan@example.com");
        
        when(userRepository.findAll()).thenReturn(List.of(usuario));

        List<UserModel> usuarios = userService.obtenerTodos();

        assertThat(usuarios).hasSize(1);
        assertThat(usuarios.get(0).getNombre()).isEqualTo("Juan Pérez");
    }

    @Test
    @DisplayName("UserService - actualizarUsuario lanza error si no existe")
    void userService_actualizarUsuario_lanzaErrorSiNoExiste() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        UserModel nuevoUsuario = new UserModel();

        assertThatThrownBy(() -> userService.actualizarUsuario(1, nuevoUsuario))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Usuario no encontrado");
    }

    @Test
    @DisplayName("UserService - crearUsuario guarda usuario correctamente")
    void userService_crearUsuario_guardaUsuarioCorrectamente() {
        UserModel usuario = new UserModel();
        usuario.setNombre("María González");
        usuario.setEmail("maria@example.com");
        
        UserModel usuarioGuardado = new UserModel();
        usuarioGuardado.setId(1);
        usuarioGuardado.setNombre("María González");
        usuarioGuardado.setEmail("maria@example.com");
        
        when(userRepository.save(usuario)).thenReturn(usuarioGuardado);

        UserModel resultado = userService.crearUsuario(usuario);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1);
        assertThat(resultado.getNombre()).isEqualTo("María González");
    }

    @Test
    @DisplayName("UserService - buscarPorId retorna usuario cuando existe")
    void userService_buscarPorId_retornaUsuarioCuandoExiste() {
        UserModel usuario = new UserModel();
        usuario.setId(1);
        usuario.setNombre("Juan Pérez");
        
        when(userRepository.findById(1)).thenReturn(Optional.of(usuario));

        UserModel resultado = userService.buscarPorId(1);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(1);
        assertThat(resultado.getNombre()).isEqualTo("Juan Pérez");
    }
}