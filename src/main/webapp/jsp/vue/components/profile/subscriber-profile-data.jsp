<script type="text/x-template" id="subscriber-profile-data-template">
    <div>
        <div class="row">
            <div class="input-field col s8">
                <input :placeholder=text_first_name
                       id="first_name"
                       type="text"
                       class="validate"
                       v-model="d_subscriber.firstName">
                <label for="first_name">
                    {{text_first_name}}
                </label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s8">
                <input :placeholder=text_last_name
                       id="last_name"
                       type="text"
                       class="validate"
                       v-model="d_subscriber.lastName">
                <label for="last_name">
                    {{text_last_name}}
                </label>
            </div>
        </div>

        <div class="row">
            <div class="input-field col s8">
                <input :placeholder=text_login
                       id="login"
                       type="text"
                       class="validate"
                       v-model="d_subscriber.user.login">
                <label for="login">
                    {{text_login}}
                </label>
            </div>
        </div>

        <div class="row">
            <a href="" @click.prevent="updateProfile" class="btn">
                {{text_update}}
            </a>
        </div>
    </div>
</script>

<script>
    Vue.component('subscriber-profile-data', {
        props: [
            'subscriber',
        ],
        template: "#subscriber-profile-data-template",
        data() {
            return {
                d_subscriber: JSON.parse(this.subscriber),
                text_first_name: strings['first_name'],
                text_last_name: strings['last_name'],
                text_login: strings['login'],
                text_update: strings['update']
            }
        },
        methods: {
            updateProfile() {
                axios.post("/api/user/profile/update", {
                    'userId': this.d_subscriber.user.id,
                    'firstName': this.d_subscriber.firstName,
                    'lastName': this.d_subscriber.lastName,
                    'login': this.d_subscriber.user.login
                }).then((response) => {
                    console.log(response);
                    if (response.data.success) {
                        Materialize.toast(strings['update_success'], 1500, "green darken-2")
                    } else {
                        Materialize.toast(strings['update_fail'], 1500, "red darken-2");
                    }
                }).catch((error) => {
                    console.log(error);
                    Materialize.toast(strings['update_fail'], 1500, "red darken-2");
                })
            },
        },
        mounted: function () { },
        computed: {}
    })
</script>
