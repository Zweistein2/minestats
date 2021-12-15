package de.zweistein2.minestats.controller

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.util.NestedServletException
import javax.servlet.RequestDispatcher
import javax.servlet.http.HttpServletRequest

@Controller
class ErrorController: ErrorController {

    @RequestMapping("/error")
    fun handleError(model: Model, request: HttpServletRequest): String {
        val message = if(request.getAttribute(RequestDispatcher.ERROR_EXCEPTION) != null) {
            (request.getAttribute(RequestDispatcher.ERROR_EXCEPTION) as NestedServletException).cause?.message ?: (request.getAttribute(RequestDispatcher.ERROR_EXCEPTION) as NestedServletException).message ?: ""
        } else {
            "Es ist ein unbekannter Fehler aufgetreten"
        }

        model.addAttribute("message", message)

        return "error"
    }
}