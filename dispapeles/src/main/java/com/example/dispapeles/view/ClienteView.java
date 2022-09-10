package com.example.dispapeles.view;

import com.example.dispapeles.controller.ClienteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@SessionAttributes("cliente")
public class ClienteView {

    @Autowired
    private ClienteController clienteController;

    @GetMapping("/consultar")
    public String consultarClientes(Model model) {

        List<ClienteDto> clientes = clienteController.consultarClientes();
        model.addAttribute("clientes", clientes);
        return "cliente";
    }

    @RequestMapping("/consultar/{numeroIdentificacion}")
    public String consultarCliente(@PathVariable(value = "numeroIdentificacion") String numeroIdentificacion, Model model) {
        ResponseEntity respuesta = clienteController.consultarCliente(numeroIdentificacion);
        model.addAttribute("cliente", (ClienteDto) respuesta.getBody());
        return "editar";
    }

    @RequestMapping(value = "/crear")
    public String crear(Model model) {
        model.addAttribute("cliente", new ClienteDto());
        return "crear";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardarCliente(ClienteDto clienteDto,
                                         Model model, RedirectAttributes flash, SessionStatus status) {
        ResponseEntity respuesta = clienteController.guardarCliente(clienteDto);

        if (respuesta.getStatusCode().is4xxClientError()) {
            model.addAttribute("error", respuesta.getBody().toString());
            return "crear";
        }

        model.addAttribute("success", respuesta.getBody().toString());
        status.setComplete();
        return "redirect:consultar";
    }

    @RequestMapping(value = "/eliminar/{numeroIdentificacion}")
    public String eliminarCliente(@PathVariable(value = "numeroIdentificacion") String numeroIdentificacion) {
        clienteController.eliminarCliente(numeroIdentificacion);

        return "redirect:/consultar";
    }

}
