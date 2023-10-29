package com.example.neurochat2

import androidx.recyclerview.widget.RecyclerView
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.OpenAIConfig
import kotlin.time.Duration.Companion.seconds

class OpenBot(val messages: RecyclerView) {

    private var config: OpenAIConfig

    private var openAI: OpenAI
    private var context: MutableList<ChatMessage>
    private var nameMessages: MutableList<String>
    private var listMessages: MutableList<String>
    val modelId = ModelId("gpt-3.5-turbo")

    init {
        config = OpenAIConfig(
            token = "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",
            timeout = Timeout(socket = 120.seconds)
        )
        openAI = OpenAI(config)
        context = mutableListOf()
        nameMessages = mutableListOf()
        listMessages = mutableListOf()
        messages.adapter = Adapter(nameMessages, listMessages)
    }

    fun addMessageUser(prompt: String) {
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

    suspend fun getResponse(prompt: String){
        try {
            this.addMessageUser(prompt)

            nameMessages.add("Пользователь")
            listMessages.add(prompt)
            messages.adapter = Adapter(nameMessages, listMessages)
            messages.scrollToPosition(listMessages.size - 1)

            val chatCompletionRequest = ChatCompletionRequest(
                model = modelId,
                messages = context
            )

            val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)

            completion.choices[0].message.content?.let { this.addMessageSystem(it) }

            nameMessages.add("Нейро")
            listMessages.add(completion.choices[0].message.content.toString())
            messages.scrollToPosition(listMessages.size - 1)
            messages.adapter = Adapter(nameMessages, listMessages)
        } catch (error: Exception) {
            nameMessages.add("Нейро")
            listMessages.add(error.toString())
            messages.scrollToPosition(listMessages.size - 1)
            messages.adapter = Adapter(nameMessages, listMessages)
        }
    }

    public fun getContext(): MutableList<String> {
        var _tempContext: MutableList<String> = mutableListOf()
        for (_temp in context) {
            _temp.content?.let { _tempContext.add(it) }
        }
        return _tempContext
    }
}