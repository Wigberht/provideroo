<script type="text/x-template" id="chat-page-template">

    <div class="chat-page">
        <h1>{{pageTitle}}</h1>
        <chat-list v-if="isChatList"/>
        <user-list v-if="isUserList"/>
        <chat-dialogue v-if="isChatDialogue"/>
    </div>
</script>

<script>
    Vue.component('chat-page', {
        template: "#chat-page-template",
        data() {
            return {
                user: window.user,
                pageTitle: strings['list_of_chats'],
                isChatList: true,
                isChatDialogue: false,
                isUserList: false,
            }
        },
        methods: {},
        mounted: function () {
            console.log("chat page mounted");
        },
        computed: {}
    })
</script>
