package de.zweistein2.minestats.services

import com.github.steveice10.mc.auth.service.SessionService
import com.github.steveice10.mc.protocol.MinecraftConstants
import com.github.steveice10.mc.protocol.MinecraftProtocol
import com.github.steveice10.mc.protocol.data.status.ServerStatusInfo
import com.github.steveice10.mc.protocol.data.status.handler.ServerInfoHandler
import com.github.steveice10.packetlib.tcp.TcpClientSession
import java.net.Proxy

class MinecraftQuery(val ip: String = "localhost", val port: Int = 25565) {
    fun status(): ServerStatusInfo? {
        val sessionService = SessionService()
        sessionService.proxy = Proxy.NO_PROXY

        var serverInfo: ServerStatusInfo? = null

        val client = TcpClientSession(ip, port, MinecraftProtocol(), null)
        client.setFlag(MinecraftConstants.SESSION_SERVICE_KEY, sessionService)
        client.setFlag(MinecraftConstants.SERVER_INFO_HANDLER_KEY, ServerInfoHandler { _, info ->
            serverInfo = info
        })

        client.connect()
        while(client.isConnected) {
            if(serverInfo != null) {
                client.disconnect("finished query")
                return serverInfo
            }
        }

        return null
    }
}