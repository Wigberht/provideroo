<script type="text/x-template" id="chat-dialogue-template">
    <div class="chat-dialogue">
        <div v-for="message in messages" class="row">
            <chat-message :message="message"/>
        </div>
        <chat-send-message ></chat-send-message>
    </div>
</script>

<script>
    Vue.component('chat-dialogue', {
        props: ['user_id', 'chat_id'],
        template: "#chat-dialogue-template",
        data() {
            return {
                chats: [1, 2, 3],
                users: [9, 8, 7],
            }
        },
        methods: {
            fetchChat(chatId) {
                axios.get("/rest/chat/get?chatId=" + chatId)
                     .then((response) => {
                         console.log(response);
                         if (response.data.length > 0) {
                             this.chat = response.data;
                         }
                     }).catch((error) => {
                        console.log(error);
                    }
                )
            },
            sendMessage() {
                const message = {
                    id: "3",
                    message: this.message,
                    userId: this.user_id
                };
                console.log("Sending message : ", message);
                this.webSocket.send(JSON.stringify(message));
            },
            initWebSocket() {
                this.webSocket = new WebSocket("ws://localhost:8081/socket/chat");
                this.webSocket.onopen = (message) => this.wsOpen(message);
                this.webSocket.onclose = (message) => this.wsClose(message);
                this.webSocket.onerror = (message) => this.wsError(message);
                this.webSocket.onmessage = (message) => this.wsGetMessage(message);
            },


            wsOpen(message) {
                console.log("connected with message: " + message);
            },

            wsClose(message) {
                console.log("socket closed with message: " + message);
            },

            wsGetMessage(message) {
                console.log("Message received " + message);
            },

            wsError(message) {
                console.log("error occured: " + message);
            }
        },
        mounted: function () {
//            this.initWebSocket();
            console.log("chat dialogua page mounted");
            this.fetchChat(this.chat_id);
        },
        computed: {}
    })
</script>
