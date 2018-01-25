<script type="text/x-template" id="chat-send-message-template">
    <div class="row">
        <div class="col s8 offset-s1">
            <input type="text" v-model="message">
        </div>
        <div class="col s2">
            <a @click="sendMessage" class="waves-effect waves-light btn">SEND</a>
        </div>
    </div>
</script>

<script>
    Vue.component('chat-send-message', {
        props: ['chat_id'],
        template: "#chat-send-message-template",
        data() {
            return {
                message: "",
                banned: false,
                toast: {
                    time: 1000,
                },

                webSocket: ""
            }
        },
        methods: {
            banToggle() {
                axios.post("/rest/user/ban", {}).then((response) => {
                    console.log(response);
                }).catch((error) => {
                    console.log(error);
                })
            },
            sendMessage() {
                const message = {
                    id: "3",
                    message: this.message,
                    user: window.user
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
            this.initWebSocket();
            console.log("chat send component mounted")
        },
        computed: {}
    })
</script>
