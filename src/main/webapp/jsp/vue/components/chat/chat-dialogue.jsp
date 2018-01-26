<script type="text/x-template" id="chat-dialogue-template">
    <div class="chat-dialogue">
        <h4>
            {{title}}
            <b>
                <template v-for="user in users">
                    <template v-if="user.id!=currentUserId">
                        {{user.login}}
                    </template>
                </template>
            </b>
        </h4>

        <div id="messages-block"
             class="messages-block"
             ref="scrollableBlock">

            <div v-for="message in messages" class="row">
                <chat-message :message="message"
                              :users="users"/>
            </div>

        </div>
        <br>
        <chat-send-message @send="sendMessage"></chat-send-message>
    </div>
</script>

<script>
    Vue.component('chat-dialogue', {
        props: ['user_id', 'chat_id'],
        template: "#chat-dialogue-template",
        data() {
            return {
                users: [],
                messages: [],
                chat: "",
                scrollableBlock: "",
                title: strings['chat_with'],
                currentUserId: window.user.id
            }
        },
        methods: {
            fetchChat(chatId) {
                axios.get("/rest/chat/get?chatId=" + chatId)
                     .then((response) => {
//                         console.log(response);
                         if (response.data) {
                             this.chat = response.data;
                             this.users = this.chat.users;
                             this.messages = this.chat.messages;
                             this.scrollToBottom();
                         }

                     }).catch((error) => {
                        console.log(error);
                    }
                )
            },

            sendMessage(message) {
                console.log("Emitted message : ", message);
                this.webSocket.send(JSON.stringify(message));
            },
            initWebSocket() {
                this.webSocket =
                    new WebSocket("ws://localhost:8081/socket/chat/" + this.chat_id);
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

            wsGetMessage(messageEvent) {
                const message = JSON.parse(messageEvent.data);
                console.log("Message received ", message);
                this.messages.push(message);
                this.scrollToBottom();
            },

            wsError(message) {
                console.log("error occured: " + message);
            },

            scrollToBottom() {
                setTimeout(() => {
                    this.$refs.scrollableBlock.scrollTop = this.$refs.scrollableBlock.scrollHeight;
                }, 400)
            }
        },
        mounted: function () {
            this.fetchChat(this.chat_id);
            this.initWebSocket();
        },
        computed: {}
    })
</script>
