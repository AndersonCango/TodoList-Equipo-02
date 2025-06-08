package madstodolist.controller;

import madstodolist.authentication.ManagerUserSession;
import madstodolist.dto.UsuarioData;
import madstodolist.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuariosController.class)
class UsuariosWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private ManagerUserSession managerUserSession;

    @Test
    void verUsuarios_ConUsuarioLogeado_MuestraListaUsuarios() throws Exception {
        // Arrange
        Long usuarioId = 1L;
        UsuarioData usuarioLogeado = new UsuarioData();
        usuarioLogeado.setId(usuarioId);
        usuarioLogeado.setEmail("user@example.com");
        usuarioLogeado.setPassword("pass");

        when(managerUserSession.usuarioLogeado()).thenReturn(usuarioId);
        when(usuarioService.findById(usuarioId)).thenReturn(usuarioLogeado);
        when(usuarioService.findAllUsuarios()).thenReturn(Arrays.asList(usuarioLogeado));

        // Act & Assert
        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk())
                .andExpect(view().name("listaUsuarios"))
                .andExpect(model().attributeExists("usuario"))
                .andExpect(model().attributeExists("usuarios"));
    }
}
