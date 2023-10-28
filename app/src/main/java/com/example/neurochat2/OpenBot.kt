package com.example.neurochat2

import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import kotlin.time.Duration.Companion.seconds

class OpenBot {

    private val config = OpenAIConfig(
        token = "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",
        timeout = Timeout(socket = 60.seconds)
    )

    private val openAI = OpenAI(config)
    private var context = mutableListOf<ChatMessage>()

    private fun addMessageUser(prompt: String) {
        context.add(
            ChatMessage(
                role = ChatRole.User,
                content = prompt
            )
        )
    }

    private fun addMessageSystem(prompt: String) {
        context.add(
            ChatMessage(
                role = ChatRole.System,
                content = prompt
            )
        )
    }

    fun clearMessage() {
        context.clear()
    }

    suspend fun getResponse(prompt: String): String? {
        this.addMessageUser(prompt)
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId("gpt-3.5-turbo-16k"),
            messages = context
        )
        val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)

        completion.choices[0].message.content?.let { this.addMessageSystem(it) }
        return completion.choices[0].message.content
    }
}