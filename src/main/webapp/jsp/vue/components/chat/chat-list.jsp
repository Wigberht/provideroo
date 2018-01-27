<script type="text/x-template" id="chat-list-template">
    <div class="chat-list">
        <h3>{{title}}</h3>
        <div v-if="chats.length==0">
            {{noChatsText}}
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
                title: strings['list_of_chats'],
                noChatsText: strings['no_active_chats']

            }
        },
        methods: {
            fetchChats() {
                axios.get("/api/user/chats?userId=" + window.user.id)
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
        },
        mounted: function () {
            this.fetchChats();
        },
        computed: {}
    })
</script>