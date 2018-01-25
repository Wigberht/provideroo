<script type="text/x-template" id="chat-message-template">
    <div class="chat-list">
        <%--<chat-send-message :user_id="user_id"/>--%>
        <div class="row">
            message
        </div>

    </div>
</script>

<script>
    Vue.component('chat-message', {
        props: ['user_id'],
        template: "#chat-message-template",
        data() {
            return {
                chats: [1, 2, 3],
                users: [9, 8, 7],
            }
        },
        methods: {
            fetchUsers() {
                axios.get("/rest/user/get")
                     .then((response) => {
                         console.log(response);
                         if (response.data.length > 0) {
                             this.users = response.data;
                         }
                     }).catch((error) => {
                        console.log(error);
                    }
                )
            },

            fetchChats() {
                axios.get("/rest/user/chat?userId=" + this.user_id)
                     .then((response) => {
                         console.log(response);
                         if (response.data.length > 0) {
                             this.users = response.data;
                         }
                     }).catch((error) => {
                        console.log(error);
                    }
                )
            },
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
            console.log("chat page mounted");
            this.fetchUsers();
        },
        computed: {}
    })
</script>
