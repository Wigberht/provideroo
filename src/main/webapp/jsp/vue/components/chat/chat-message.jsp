<script type="text/x-template" id="chat-message-template">
    <div>
        <template v-if="isMine">
            <p class="right message-right message-block word-wrap">
                {{message.message}}
            </p>
        </template>

        <template v-if="!isMine">
            <p class="left message-left message-block word-wrap">
                <b>{{messageAuthor.login}}</b> : {{message.message}}
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
            getMessageUser() {
                return this.users.find((messageUser) => {
                    return messageUser.id == this.message.userId;
                });
            }
        },
        mounted: function () {
            this.messageAuthor = this.getMessageUser();
        },
        computed: {}
    })
</script>
