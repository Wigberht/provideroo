<script type="text/x-template" id="chat-list-template">
    <div class="chat-list">
        <h3>{{title}}</h3>
        <div v-if="chats.length==0">
            No active chats
        </div>
        <div v-if="chats.length>0">
            <div v-for="chat in chats" class="row">
                <div @click="toChat(chat.id)" class="card-panel blue lighten-3">
                    {{chat.title}}
                </div>
            </div>
        </div>
        <a class="btn-floating btn-large waves-effect waves-light red darken-2 right"
           @click="toUserList">

            <i class="material-icons">add</i>
        </a>
    </div>
</script>

<script>
    Vue.component('chat-list', {
        template: "#chat-list-template",
        data() {
            return {
                chats: [],
                title:strings['list_of_chats']
            }
        },
        methods: {
            fetchChats() {
                axios.get("/rest/user/chats?userId=" + window.user.id)
                     .then((response) => {
                         console.log("chats", response.data);
                         if (response.data.length > 0) {
                             this.chats = response.data;
                         }
                     }).catch((error) => {
                        console.log(error);
                    }
                )
            },

            toChat(chatId) {
                this.$emit("toChat", chatId);
            },

            toUserList() {
                this.$emit("toUserList");
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
            console.log("chat list mounted");
            this.fetchChats();
        },
        computed: {}
    })
</script>
