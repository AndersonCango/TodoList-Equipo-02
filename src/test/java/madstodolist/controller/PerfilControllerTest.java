package madstodolist.controller;



import madstodolist.service.UsuarioService;
import madstodolist.authentication.ManagerUserSession;
import madstodolist.dto.UsuarioData;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PerfilController.class)
public class PerfilControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private ManagerUserSession managerUserSession;

    @Test
    public void testVistaPerfil() throws Exception {
        // Simula que hay un usuario logueado con id 1L
        when(managerUserSession.usuarioLogeado()).thenReturn(1L);

        // Simula que usuarioService encuentra un usuario con id 1L
        UsuarioData usuarioMock = new UsuarioData();
        usuarioMock.setId(1L); // O el método para setear el ID
        usuarioMock.setNombre("Test User");
        // configura otros campos que uses en la vista

        when(usuarioService.findById(1L)).thenReturn(usuarioMock);

        mockMvc.perform(get("/perfil"))
                .andExpect(status().isOk())
                .andExpect(view().name("perfil"))
                .andExpect(model().attributeExists("usuario"));
    }
}

