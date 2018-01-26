<script type="text/x-template" id="chat-message-template">
    <div>
        <template v-if="isMine">
            <p class="right message-right message-block word-wrap">
                {{message.message}}
            </p>
        </template>

        <template v-if="!isMine">
            <p class="left message-left message-block word-wrap">
                <b>{{messageAuthor}}</b> : {{message.message}}
            </p>
        </template>
    </div>
</script>

<script>
    Vue.component('chat-message', {
        props: ['user_id', 'message', 'users'],
        template: "#chat-message-template",
        data() {
            return {
                isMine: this.message.userId == window.user.id,
                messageAuthor: ""
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
        },
        mounted: function () {
            this.messageAuthor = this.users.find((messageUser) => {
                return messageUser.id == this.message.userId;
            }).login;
        },
        computed: {}
    })
</script>
